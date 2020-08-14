package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarMaker;

import java.util.List;

public interface CarMakerService {
    //todo page
    //todo dto?
    List<CarMaker> getMakers(String searchQuery);

    //todo dto?
    CarMaker getById(Integer id);

    //todo dto?
    CarMaker add(CarMaker maker);

    //todo dto?
    void update(Integer id, CarMaker maker);

    void delete(Integer id);
}
