package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<User, String> {
    //todo entity graph
    List<User> getAllWithoutCars();

    //todo entity graph
    List<User> getWithoutCarsByName(@Param("q") String q);
}
