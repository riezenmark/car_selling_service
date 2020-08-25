package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarModel;

import java.util.List;

//todo rename
public interface CarModelService {
    List<CarModel> getAllModelsOfMaker(String makerName);

    void updateOne(CarModel previousModel, CarModel model);

    void deleteOne(CarModel model);
}
