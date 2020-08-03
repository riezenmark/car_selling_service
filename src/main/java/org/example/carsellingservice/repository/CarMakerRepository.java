package org.example.carsellingservice.repository;

import org.example.carsellingservice.dao.MakerDao;
import org.example.carsellingservice.domain.Maker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarMakerRepository extends CrudRepository<Maker, Integer> {
    Maker findByName(String name);

    @Query(
            "SELECT new org.example.carsellingservice.dao.MakerDao("
                    + "m.id, m.name) from Maker m"
    )
    Iterable<MakerDao> getAllWithoutModels();
}
