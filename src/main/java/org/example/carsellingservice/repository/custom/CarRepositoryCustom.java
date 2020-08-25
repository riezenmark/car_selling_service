package org.example.carsellingservice.repository.custom;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;

import java.util.List;

public interface CarRepositoryCustom {
    List<Car> findCarsByParameters(Integer makerId, Long modelId, Integer priceFrom,
                                   Integer priceTo, Integer yearFrom, Integer yearTo,
                                   List<Transmission> transmission, List<EngineType> engineType);
}
