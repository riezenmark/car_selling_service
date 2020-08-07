package org.example.carsellingservice.service.api;

import org.example.carsellingservice.dao.MakerDao;

public interface CarMakerService {
    Iterable<MakerDao> getAllWithoutModels();
}
