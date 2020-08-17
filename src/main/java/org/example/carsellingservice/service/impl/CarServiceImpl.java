package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    //todo page, dto?
    @Override
    @Transactional(readOnly = true)
    public List<Car> getCars(
            Integer makerId, Long modelId, Integer priceFrom,
            Integer priceTo, Integer yearFrom, Integer yearTo,
            List<String> transmission, List<String> engineType
    ) {
        List<Car> carsFromRepository;
        if (Stream.of(makerId, modelId, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType).anyMatch(Objects::nonNull)) {
            carsFromRepository = carRepository.findCarsByParameters(makerId, modelId, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType);
        } else {
            carsFromRepository = carRepository.findAll();
        }
        return carsFromRepository;
    }

    @Override
    public Car getById(Long id) {
        return null;
    }

    @Override
    public Car add(Car car) {
        return null;
    }

    @Override
    public Car update(Long id, Car car) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
