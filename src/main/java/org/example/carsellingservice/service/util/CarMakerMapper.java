package org.example.carsellingservice.service.util;

import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.dto.CarMakerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMakerMapper {
    List<CarMakerDto> map(List<CarMaker> makers);

    CarMakerDto map(CarMaker maker);
}
