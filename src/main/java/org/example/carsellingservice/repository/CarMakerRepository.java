package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarMakerRepository extends JpaRepository<CarMaker, Integer> {

    @Query("SELECT m from CarMaker m where upper(m.name) like %:searchQuery%")
    List<CarMaker> findAllByNameLike(String searchQuery);

    Optional<CarMaker> findByName(String name);
}
