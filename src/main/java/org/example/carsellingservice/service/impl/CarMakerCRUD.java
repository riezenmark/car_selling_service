package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.dao.MakerDao;
import org.example.carsellingservice.domain.Maker;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarMakerCRUD implements CarMakerService {

    private final CarMakerRepository makerRepository;

    @Autowired
    public CarMakerCRUD(CarMakerRepository makerRepository) {
        this.makerRepository = makerRepository;
    }

    @Override
    public Iterable<MakerDao> getAllWithoutModels() {
        return makerRepository.getAllWithoutModels();
    }
}
