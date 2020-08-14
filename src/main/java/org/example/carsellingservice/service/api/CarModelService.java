package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.dto.CarModelDto;

import java.util.List;

public interface CarModelService {
    //todo page
    List<CarModelDto> getModels(String searchQuery, Integer makerId);

    //todo dto?
    CarModel getById(Long id);

    //todo dto?
    CarModel add(CarModel model);

    //todo dto?
    void update(Long id, CarModel model);

    void delete(Long id);
}
