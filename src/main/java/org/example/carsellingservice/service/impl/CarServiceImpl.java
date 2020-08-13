package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.*;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    @Value("${upload.path}:/home/riezenmark/uploads")
    private String uploadPath;

    //todo Автовайрим сервисы, передаём дто (mapStruct)
    private final CarRepository carRepository;
    private final CarMakerRepository makerRepository;
    private final CarModelRepository modelRepository;
    private final UserDetailsRepository userRepository;

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo,
            Integer yearFrom, Integer yearTo, List<String> transmission, List<String> engineType
    ) {
        List<Car> cars;
        if (manufacturer != null && !manufacturer.equals("")) {
            cars = StreamSupport.stream(
                    carRepository.findByMaker(
                            makerRepository.findByName(manufacturer)
                    ).spliterator(), false
            ).collect(Collectors.toList());
            cars = this.filterCars(cars, model, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType);
        } else {
            cars = StreamSupport.stream(this.getAll().spliterator(), false).collect(Collectors.toList());
        }
        return cars;
    }

    @Override
    public void addCarForUser(
            User user, String modelName, String makerName, int price, int yearOfProduction,
            String transmission, String engineType, MultipartFile file
    ) throws IOException {
        CarMaker carMaker = makerRepository.findByName(makerName);
        CarModel model = modelRepository.findByName(modelName);
        if (this.thereIsNoCarWithFields(carMaker, model, yearOfProduction, engineType, transmission)) {
            Car car = this.createNewCarWithFields(carMaker, model, price, yearOfProduction, transmission, engineType);
            this.uploadFileIfExists(car, file);
            this.addCarForUser(car, user);
            this.saveCarAndUser(car, user);
        }
    }

    @Override
    public Integer getMaximumCarPrice() {
        return carRepository.getMaximumCarPrice();
    }

    @Override
    public List<Car> getCarsOfUserWithId(String id) {
        User user = userRepository.findById(id).orElse(null);
        return carRepository.findByUser(user);
    }

    @Override
    public void deleteCarWithId(Long id, User user) {
        user = userRepository.findById(user.getId()).orElse(null);
        Car car = carRepository.findById(id).orElse(null);
        if (this.userHasCar(user, car)) {
            this.deleteFileIfExists(car.getFilename());
            this.removeCarWithIdFromUserCars(user, id);
            userRepository.save(user);
            carRepository.delete(car);
        }
    }

    @Override
    public void updateCar(Car car, User user) {
        user = userRepository.findById(user.getId()).orElse(null);
        if (this.userHasCar(user, car)) {
            Car carFromDatabase = carRepository.findById(car.getId()).orElse(null);
            if (carFromDatabase != null) {
                this.fillCarFields(carFromDatabase, car);
                carRepository.save(carFromDatabase);
            }
        }
    }

    //todo hibernate criteria
    private List<Car> filterCars(
            List<Car> cars, String model, Integer priceFrom, Integer priceTo,
            Integer yearFrom, Integer yearTo, List<String> transmission, List<String> engineType
    ) {
        Range price = new Range(priceFrom, priceTo);
        Range year = new Range(yearFrom, yearTo);
        this.validateRange(price);
        this.validateRange(year);
        return this.filterCarsByModelIfExists(
                this.filterCarsByPrice(
                        this.filterCarsByYear(
                                this.filterCarsByTransmissionIfExists(
                                        this.filterCarsByEngineTypeIfExists(
                                                cars, engineType
                                        ), transmission
                                ), year
                        ), price
                ), model
        );
    }

    private List<Car> filterCarsByModelIfExists(List<Car> cars, String model) {
        if (model != null && !model.equals("")) {
            cars = cars.stream().filter(car -> car.getModel().getName().equals(model)).collect(Collectors.toList());
        }
        return cars;
    }

    private void validateRange(Range range) {
        if (range.from == null || range.from < 0) {
            range.from = 0;
        }
        if (range.to == null || range.to < 0) {
            range.to = 0;
        }
        if (range.from > range.to) {
            int tmp = range.from;
            range.from = range.to;
            range.to = tmp;
        }
    }

    private List<Car> filterCarsByPrice(List<Car> cars, Range price) {
        if (price.from != 0) {
            cars = cars.stream().filter(
                    car -> car.getPrice() >= price.from
            ).collect(Collectors.toList());
        }
        if (price.to != 0) {
            cars = cars.stream().filter(
                    car -> car.getPrice() <= price.to
            ).collect(Collectors.toList());
        }
        return cars;
    }

    private List<Car> filterCarsByYear(List<Car> cars, Range year) {
        if (year.from != 0) {
            cars = cars.stream().filter(
                    car -> car.getYearOfProduction() >= year.from
            ).collect(Collectors.toList());
        }
        if (year.to != 0) {
            cars = cars.stream().filter(
                    car -> car.getYearOfProduction() <= year.to
            ).collect(Collectors.toList());
        }
        return cars;
    }

    private List<Car> filterCarsByTransmissionIfExists(List<Car> cars, List<String> transmission) {
        if (transmission != null && transmission.size() != 0) {
            cars = cars.stream().filter(
                    car -> transmission.contains(car.getTransmission().name())
            ).collect(Collectors.toList());
        }
        return cars;
    }

    private List<Car> filterCarsByEngineTypeIfExists(List<Car> cars, List<String> engineType) {
        if (engineType != null && engineType.size() != 0) {
            cars = cars.stream().filter(
                    car -> engineType.contains(car.getEngineType().name())
            ).collect(Collectors.toList());
        }
        return cars;
    }

    private void uploadFileIfExists(Car car, MultipartFile file) throws IOException {
        if (file != null) {
            String filename = file.getOriginalFilename();
            if (filename != null && !filename.isEmpty()) {
                setFile(car, file);
            }
        }
    }

    private void addCarForUser(Car car, User user) {
        Set<Car> userCars = user.getAddedCars();
        if (userCars == null) {
            userCars = new HashSet<>();
        }
        userCars.add(car);
    }

    private void saveCarAndUser(Car car, User user) {
        user = userRepository.save(user);
        car.setUser(user);
        carRepository.save(car);
    }

    private boolean thereIsNoCarWithFields(CarMaker carMaker, CarModel model, int yearOfProduction, String engineType, String transmission) {
        return carRepository.findByMakerAndModelAndYearOfProductionAndEngineTypeAndTransmission(
                carMaker, model, yearOfProduction,
                EngineType.valueOf(engineType),
                TransmissionType.valueOf(transmission)
        ) == null;
    }

    //todo lombok builder
    private Car createNewCarWithFields(
            CarMaker carMaker, CarModel model, int price, int yearOfProduction,
            String transmission, String engineType
    ) {
        Car car = new Car();
        car.setMaker(carMaker);
        car.setModel(model);
        car.setPrice(price);
        car.setYearOfProduction(yearOfProduction);
        car.setTransmission(TransmissionType.valueOf(transmission));
        car.setEngineType(EngineType.valueOf(engineType));
        return car;
    }

    private boolean userHasCar(User user, Car car) {
        return user != null && car != null && user.getAddedCars().contains(car);
    }

    private void deleteFileIfExists(String filename) {
        File file = new File(uploadPath + "/" + filename);
        if (file.exists()) {
            file.delete();
        }
    }

    private void removeCarWithIdFromUserCars(User user, Long id) {
        Set<Car> usersCars = user.getAddedCars();
        usersCars = usersCars.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toSet());
        user.setAddedCars(usersCars);
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

    //todo lombok builder
    private void fillCarFields(Car carFromDatabase, Car car) {
        carFromDatabase.setMaker(makerRepository.findByName(car.getMaker().getName()));
        carFromDatabase.setModel(modelRepository.findByName(car.getModel().getName()));
        carFromDatabase.setPrice(car.getPrice());
        carFromDatabase.setYearOfProduction(car.getYearOfProduction());
        carFromDatabase.setEngineType(car.getEngineType());
        carFromDatabase.setTransmission(car.getTransmission());
    }

    //todo Возможно заменить
    private static final class Range {
        private Integer from;
        private Integer to;

        public Range(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }
    }
}
