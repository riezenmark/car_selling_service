package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getCars(final Integer makerId, final Long modelId, final Integer priceFrom,
                      final Integer priceTo, final Integer yearFrom, final Integer yearTo,
                      final List<Transmission> transmission, final List<EngineType> engineType);


    CarDto getById(final Long id);

    CarDto add(final Car car);

    CarDto update(final Long id, final Car car);

    void deleteById(final Long id);
}
