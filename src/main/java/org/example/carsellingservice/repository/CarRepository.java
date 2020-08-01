package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
