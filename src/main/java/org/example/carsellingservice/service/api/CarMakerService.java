package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.dto.CarMakerDto;

import java.util.List;

public interface CarMakerService {

    //todo page
    List<CarMakerDto> getMakers(String searchQuery);

    CarMaker getById(Integer id);

    CarMakerDto add(CarMaker maker);

    //todo dto?
    void update(Integer id, CarMaker maker);

    void delete(Integer id);
}
