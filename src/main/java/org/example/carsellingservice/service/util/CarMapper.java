package org.example.carsellingservice.service.util;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.dto.CarDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    List<CarDto> map(List<Car> cars);

    CarDto map(Car car);
}
