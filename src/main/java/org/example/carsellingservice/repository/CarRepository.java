package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    //todo entity graph
    //todo find by car maker name?
    List<Car> findByMaker(CarMaker carMaker);

    @Query("SELECT max(c.price) from Car c")
    Integer getMaximumCarPrice();

    //todo entity graph
    //todo find by user_?
    List<Car> findByUser(User user);
}
