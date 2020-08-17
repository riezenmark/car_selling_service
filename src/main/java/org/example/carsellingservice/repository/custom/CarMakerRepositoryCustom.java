package org.example.carsellingservice.repository.custom;

import org.example.carsellingservice.domain.CarMaker;

public interface CarMakerRepositoryCustom {
    void bulkDeleteCascade(final CarMaker maker);
}
