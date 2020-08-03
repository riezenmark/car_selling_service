package org.example.carsellingservice.service.impl;

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

    public Maker getByName(String name) {
        return makerRepository.findByName(name);
    }

    @Override
    public Iterable<Maker> getAll() {
        return null;
    }

    @Override
    public Iterable<Maker> getAllWithReferenceID(Integer integer) {
        return null;
    }

    @Override
    public Maker addOne(Maker maker) {
        return makerRepository.save(maker);
    }

    @Override
    public Maker updateOne(Maker maker) {
        return null;
    }

    @Override
    public Maker deleteOne(Maker maker) {
        return null;
    }
}
