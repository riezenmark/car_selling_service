package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.*;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarCRUD implements CarService {

    @Value("${upload.path}")
    private String uploadPath;

    private final CarRepository carRepository;
    private final CarMakerRepository makerRepository;
    private final CarModelRepository modelRepository;
    private final UserDetailsRepository userRepository;

    @Autowired
    public CarCRUD(
            CarRepository carRepository, CarMakerRepository makerRepository,
            CarModelRepository modelRepository, UserDetailsRepository userRepository
    ) {
        this.carRepository = carRepository;
        this.makerRepository = makerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Iterable<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo,
            Integer yearFrom, Integer yearTo, List<String> transmission, List<String> engineType
    ) {
        if (manufacturer != null && !manufacturer.equals("")) {
            List<Car> cars = StreamSupport.stream(
                    carRepository.findByMaker(
                            makerRepository.findByName(manufacturer)
                    ).spliterator(), false
            ).collect(Collectors.toList());
            if (model != null && !model.equals("")) {
                cars = cars.stream().filter(car -> car.getModel().getName().equals(model)).collect(Collectors.toList());
            }
            if (priceFrom == null || priceFrom < 0) {
                priceFrom = 0;
            }
            if (priceTo == null || priceTo < 0) {
                priceTo = 0;
            }
            if (priceFrom > priceTo) {
                int tmp = priceFrom;
                priceFrom = priceTo;
                priceTo = tmp;
            }
            if (priceFrom != 0) {
                int finalPriceFrom = priceFrom;
                cars = cars.stream().filter(
                        car -> car.getPrice() >= finalPriceFrom
                ).collect(Collectors.toList());
            }
            if (priceTo != 0) {
                int finalPriceTo = priceTo;
                cars = cars.stream().filter(
                        car -> car.getPrice() <= finalPriceTo
                ).collect(Collectors.toList());
            }
            if (yearFrom == null || yearFrom < 0) {
                yearFrom = 0;
            }
            if (yearTo == null || yearTo < 0) {
                yearTo = 0;
            }
            if (yearFrom > yearTo) {
                int tmp = yearFrom;
                yearFrom = yearTo;
                yearTo = tmp;
            }
            if (yearFrom != 0) {
                int finalYearFrom = yearFrom;
                cars = cars.stream().filter(
                        car -> car.getYearOfProduction() >= finalYearFrom
                ).collect(Collectors.toList());
            }
            if (yearTo != 0) {
                int finalYearTo = yearTo;
                cars = cars.stream().filter(
                        car -> car.getYearOfProduction() <= finalYearTo
                ).collect(Collectors.toList());
            }
            if (transmission != null && transmission.size() != 0) {
                cars = cars.stream().filter(
                        car -> transmission.contains(car.getTransmission().name())
                ).collect(Collectors.toList());
            }
            if (engineType != null && engineType.size() != 0) {
                cars = cars.stream().filter(
                        car -> engineType.contains(car.getEngineType().name())
                ).collect(Collectors.toList());
            }
            return cars;
        } else {
            return this.getAll();
        }
    }

    @Override
    public void addCarForUser(
            User user, String modelName, String makerName, int price, int yearOfProduction,
            String transmission, String engineType, MultipartFile file
    ) throws IOException {
        Maker maker = makerRepository.findByName(makerName);
        Model model = modelRepository.findByName(modelName);
        if (carRepository.findByMakerAndModelAndYearOfProductionAndEngineTypeAndTransmission(
                maker, model, yearOfProduction,
                EngineType.valueOf(engineType),
                Transmission.valueOf(transmission)
        ) == null) {
            Car car = new Car();
            car.setMaker(maker);
            car.setModel(model);
            car.setPrice(price);
            car.setYearOfProduction(yearOfProduction);
            car.setTransmission(Transmission.valueOf(transmission));
            car.setEngineType(EngineType.valueOf(engineType));
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                setFile(car, file);
            }
            Set<Car> userCars = user.getAddedCars();
            if (userCars == null) {
                userCars = new HashSet<>();
            }
            userCars.add(car);
            user = userRepository.save(user);
            car.setUser(user);
            carRepository.save(car);
        }
    }

    @Override
    public Integer getMaximumCarPrice() {
        return carRepository.getMaximumCarPrice();
    }

    @Override
    public Iterable<Car> getCarsOfUserWithId(String id) {
        User user = userRepository.findById(id).orElse(null);
        return carRepository.findByUser(user);
    }

    @Override
    public void deleteCarWithId(Long id, User user) {
        user = userRepository.findById(user.getId()).orElse(null);
        Car car = carRepository.findById(id).orElse(null);
        if (user != null && car != null) {
            String filename = car.getFilename();
            File file = new File(uploadPath + "/" + filename);
            if (file.exists()) {
                file.delete();
            }
            Set<Car> usersCars = user.getAddedCars();
            usersCars = usersCars.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toSet());
            user.setAddedCars(usersCars);
            userRepository.save(user);
            carRepository.delete(car);
        }
    }

    private void setFile(Car car, MultipartFile file) throws IOException {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdir();
        }
        String uniqueFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + uniqueFilename));
        car.setFilename(uniqueFilename);
    }
}
