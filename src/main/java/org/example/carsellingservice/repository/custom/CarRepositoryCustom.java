package org.example.carsellingservice.repository.custom;

import org.example.carsellingservice.domain.Car;

import java.util.List;

public interface CarRepositoryCustom {
    //todo page
    //todo dto?
    List<Car> findCarsByParameters(Integer makerId, Long modelId, Integer priceFrom,
                                   Integer priceTo, Integer yearFrom, Integer yearTo,
                                   List<String> transmission, List<String> engineType);
}
