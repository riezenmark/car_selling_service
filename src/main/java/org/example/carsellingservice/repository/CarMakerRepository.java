package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarMakerRepository extends JpaRepository<CarMaker, Integer> {

    //todo entity graph
    Optional<CarMaker> findByName(String name);

    //todo entity graph
    List<CarMaker> getAllWithoutModels();
}
