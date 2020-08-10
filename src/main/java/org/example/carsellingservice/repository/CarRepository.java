package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByMaker(Maker maker);

    @Query("SELECT max(c.price) from Car c")
    Integer getMaximumCarPrice();

    Iterable<Car> findByUser(User user);
}
