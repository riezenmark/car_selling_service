package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Maker;
import org.springframework.data.repository.CrudRepository;

public interface CarMakerRepository extends CrudRepository<Maker, Integer> {
}
