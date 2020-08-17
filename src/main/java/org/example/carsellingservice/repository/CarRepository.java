package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.repository.custom.CarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {
}
