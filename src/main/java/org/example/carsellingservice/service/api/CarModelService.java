package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarModel;

import java.util.List;

public interface CarModelService {
    List<CarModel> getModels(final String searchQuery, final Integer makerId);

    CarModel getById(final Long id);

    CarModel add(final CarModel model);

    CarModel update(final Long id, final CarModel model);

    void deleteById(final Long id);
}
