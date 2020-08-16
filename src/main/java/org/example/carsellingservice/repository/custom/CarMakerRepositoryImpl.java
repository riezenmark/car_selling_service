package org.example.carsellingservice.repository.custom;

import org.example.carsellingservice.domain.CarMaker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CarMakerRepositoryImpl implements CarMakerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void cascadeDelete(CarMaker maker) {
        entityManager.find(CarMaker.class, maker.getId());
        Query query = entityManager.createQuery("DELETE from CarModel where maker.id = ?1");
        query.setParameter(1, maker.getId());
        query.executeUpdate();
        entityManager.remove(maker);
    }
}
