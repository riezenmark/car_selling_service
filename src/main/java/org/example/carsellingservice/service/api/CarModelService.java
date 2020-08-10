package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Model;

public interface CarModelService {
    Iterable<Model> getAllModelsOfMaker(String makerName);

    void updateOne(Model previousModel, Model model);

    void deleteOne(Model model);
}
