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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CarCRUD implements CarService {

    @Value("${upload.path}")
    private String uploadPath;

    private final CarRepository carRepository;
    private final CarMakerRepository makerRepository;
    private final CarModelRepository modelRepository;

    @Autowired
    public CarCRUD(CarRepository carRepository, CarMakerRepository makerRepository, CarModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.makerRepository = makerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void addCarForUser(
            User user, String modelName, String makerName, int price, int yearOfProduction,
            String transmission, String engineType, MultipartFile file
    ) throws IOException {
        Maker maker =  makerRepository.findByName(makerName);
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
            car.setUser(user);
            carRepository.save(car);
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
