package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.repository.custom.CarRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {
    @Override
    @EntityGraph(value = "carWithMakerAndModelAndUser")
    List<Car> findAll();

    @EntityGraph(value = "carWithMakerAndModelAndUser")
    List<Car> findByUser_Id(Long userId);

    @Query("SELECT c from Car c where c.id = :id")
    @EntityGraph(value = "carWithMakerAndModelAndUser")
    Optional<Car> findByIdWithMakerAndModel(Long id);

    boolean existsByIdAndUser_Id(Long id, Long userId);
}
