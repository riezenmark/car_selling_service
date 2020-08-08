package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.*;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByMakerAndModelAndYearOfProductionAndEngineTypeAndTransmission(
            Maker maker, Model model, int yearOfProduction, EngineType engineType, Transmission transmission
    );
}
