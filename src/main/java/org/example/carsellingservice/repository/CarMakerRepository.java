package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.repository.custom.CarMakerRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarMakerRepository extends JpaRepository<CarMaker, Integer>, CarMakerRepositoryCustom {
    @Override
    @Query("from CarMaker m order by m.name")
    List<CarMaker> findAll();

    @Query("SELECT m from CarMaker m where upper(m.name) like %:searchQuery% order by m.name")
    List<CarMaker> findAllByNameLike(final String searchQuery);

    @Query("SELECT m from CarMaker m where m.id = :id")
    @EntityGraph(attributePaths = {"models"})
    Optional<CarMaker> findByIdWithModels(final Integer id);

    Optional<CarMaker> findByName(final String name);
}
