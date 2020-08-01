package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCRUD implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarCRUD(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
