package org.example.carsellingservice.repository;

import org.example.carsellingservice.dao.MakerDao;
import org.example.carsellingservice.domain.Maker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Таблица производитлей (марок) машин.
 */
public interface CarMakerRepository extends CrudRepository<Maker, Integer> {

    /**
     * Находит марку машины по названию.
     * @param name - название марки машины.
     * @return Найденная марка машины.
     */
    Maker findByName(String name);

    /**
     * Возвращает все существующие в таблице марки машн без их моделей.
     * @return Марки машин без моделей.
     */
    @Query(
            "SELECT new org.example.carsellingservice.dao.MakerDao("
                    + "m.id, m.name) from Maker m"
    )
    Iterable<MakerDao> getAllWithoutModels();
}
