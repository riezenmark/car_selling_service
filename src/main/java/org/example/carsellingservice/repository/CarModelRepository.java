package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Model;
import org.springframework.data.repository.CrudRepository;

public interface CarModelRepository extends CrudRepository<Model, Integer> {
    Model findByName(String name);
}
