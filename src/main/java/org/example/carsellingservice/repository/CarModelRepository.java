package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel, Long>, JpaSpecificationExecutor<CarModel> {

    @Override
    @EntityGraph(attributePaths = {"id", "name"})
    List<CarModel> findAll();

    @Override
    @EntityGraph(attributePaths = {"id", "name"})
    List<CarModel> findAll(Specification<CarModel> specification);

    @Override
    @EntityGraph(attributePaths = {"id", "name"})
    Optional<CarModel> findById(final Long id);

    @EntityGraph(attributePaths = {"id", "name"})
    Optional<CarModel> findByNameAndMaker_id(final String name, final Integer id);
}
