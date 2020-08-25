package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {

    //todo entity graph
    Optional<CarModel> findByName(String name);
}
