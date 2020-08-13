package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarMaker;

import java.util.List;

public interface CarMakerService {
    List<CarMaker> getAllWithoutModels();
}
