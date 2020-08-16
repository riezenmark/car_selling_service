package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarModel;

import java.util.List;

public interface CarModelService {

    List<CarModel> getModels(String searchQuery, Integer makerId);

    CarModel getById(Long id);

    //todo dto?
    CarModel add(CarModel model);

    //todo dto?
    void update(Long id, CarModel model);

    void delete(Long id);
}
