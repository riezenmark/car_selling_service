package org.example.carsellingservice.repository.custom.impl;

import lombok.var;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.repository.custom.CarRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    //todo page
    //todo dto?
    @Override
    public List<Car> findCarsByParameters(
            Integer makerId, Long modelId, Integer priceFrom,
            Integer priceTo, Integer yearFrom, Integer yearTo,
            List<String> transmission, List<String> engineType
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);

        Root<Car> carRoot = query.from(Car.class);
        List<Predicate> predicates = new ArrayList<>();

        fillPredicates(makerId, modelId, priceFrom, priceTo, yearFrom, yearTo,
                transmission, engineType, criteriaBuilder, carRoot, predicates);
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    private void fillPredicates(
            Integer makerId, Long modelId, Integer priceFrom,
            Integer priceTo, Integer yearFrom, Integer yearTo,
            List<String> transmission, List<String> engineType,
            CriteriaBuilder criteriaBuilder, Root<Car> carRoot, List<Predicate> predicates) {
        if (makerId != null) {
            predicates.add(criteriaBuilder.equal(carRoot.get("maker").get("id"), makerId));
        }
        if (modelId != null) {
            predicates.add(criteriaBuilder.equal(carRoot.get("model").get("id"), modelId));
        }
        if (priceFrom != null) {
            predicates.add(criteriaBuilder.ge(carRoot.get("price"), priceFrom));
        }
        if (priceTo != null) {
            predicates.add(criteriaBuilder.le(carRoot.get("price"), priceTo));
        }
        if (yearFrom != null) {
            predicates.add(criteriaBuilder.ge(carRoot.get("yearOfProduction"), yearFrom));
        }
        if (yearTo != null) {
            predicates.add(criteriaBuilder.le(carRoot.get("yearOfProduction"), yearTo));
        }
        if (transmission != null && !transmission.isEmpty()) {
            In<String> inClause = criteriaBuilder.in(carRoot.get("transmission"));
            for (var t : transmission) {
                inClause.value(t);
            }
            predicates.add(inClause);
        }
        if (engineType != null && !engineType.isEmpty()) {
            In<String> inClause = criteriaBuilder.in(carRoot.get("engineType"));
            for (var t : engineType) {
                inClause.value(t);
            }
            predicates.add(inClause);
        }
    }
}
