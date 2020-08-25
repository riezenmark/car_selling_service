package org.example.carsellingservice.repository.specification;

import org.example.carsellingservice.domain.CarModel;
import org.springframework.data.jpa.domain.Specification;

public class CarModelSpecification {
    public static Specification<CarModel> carModelsWithNameLike(final String name) {
        return (modelRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(modelRoot.get("name")), "%" + name.toUpperCase() + "%");
    }

    public static Specification<CarModel> carModelsOfCarMakerWithId(final Integer id) {
        return (modelRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(modelRoot.get("maker").get("id"), id);
    }

    public static Specification<CarModel> carModelsOfMakerWithIdAndNameLike(final Integer id, final String name) {
        return Specification.where(carModelsOfCarMakerWithId(id).and(carModelsWithNameLike(name)));
    }
}
