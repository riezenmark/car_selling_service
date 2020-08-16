package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Override
    @EntityGraph(attributePaths = {"id", "name"})
    List<CarModel> findAll();

    @Query("SELECT m from CarModel m where upper(m.name) like %:searchQuery%")
    List<CarModel> findAllByNameLike(String searchQuery);

    Optional<CarModel> findByNameAndMaker_id(String name, Integer id);
}
