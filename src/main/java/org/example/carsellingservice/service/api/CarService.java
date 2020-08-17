package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(Integer makerId, Long modelId, Integer priceFrom,
                      Integer priceTo, Integer yearFrom, Integer yearTo,
                      List<String> transmission, List<String> engineType);


    Car getById(Long id);

    Car add(Car car);

    Car update(Long id, Car car);

    void deleteById(Long id);
}
