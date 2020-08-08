package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Model;

public interface CarModelService extends CrudService<Model> {
    Iterable<Model> getAllModelsOfMaker(String makerName);
}
