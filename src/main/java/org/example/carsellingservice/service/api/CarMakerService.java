package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.dto.CarMakerDto;

import java.util.List;

public interface CarMakerService {
    List<CarMakerDto> getMakers(final String searchQuery);

    CarMaker getById(final Integer id);

    CarMakerDto add(final CarMaker maker);

    CarMakerDto update(final Integer id, final CarMaker maker);

    void deleteById(final Integer id);

    boolean existsById(final Integer id);

    boolean carMakerOfModelHasModelWithName(final CarModel model, final String name);
}
