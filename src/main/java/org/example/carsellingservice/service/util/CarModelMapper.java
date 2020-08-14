package org.example.carsellingservice.service.util;

import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.dto.CarModelDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarModelMapper {
    List<CarModelDto> map(List<CarModel> models);
}
