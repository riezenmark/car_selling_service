package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Таблица машин.
 */
public interface CarRepository extends CrudRepository<Car, Integer> {

    /**
     * Находит все машины данной марки.
     * @param maker - марка машины.
     * @return Найденные машины.
     */
    Iterable<Car> findByMaker(Maker maker);

    /**
     * Находит максимальную цену машины.
     * @return Максимальная цена машины.
     */
    @Query("SELECT max(c.price) from Car c")
    Integer getMaximumCarPrice();

    /**
     * Находит все машины, добавленные пользователем.
     * @param user - пользователь.
     * @return Машины, добавленные пользователем.
     */
    Iterable<Car> findByUser(User user);
}
