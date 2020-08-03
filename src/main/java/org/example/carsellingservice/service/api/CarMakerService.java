package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Maker;

public interface CarMakerService extends CrudService<Maker, Integer> {
    Maker getByName(String name);
}
