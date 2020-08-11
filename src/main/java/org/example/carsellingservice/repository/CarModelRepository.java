package org.example.carsellingservice.repository;

import org.example.carsellingservice.domain.Model;
import org.springframework.data.repository.CrudRepository;

/**
 * Таблица моделей машин.
 */
public interface CarModelRepository extends CrudRepository<Model, Integer> {

    /**
     * Находит модель машы по названию.
     * @param name - название модели машины.
     * @return Найденная модель машины.
     */
    Model findByName(String name);
}
