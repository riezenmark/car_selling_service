package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Maker;
import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarModelCRUD implements CarModelService {

    private final CarModelRepository modelRepository;
    private final CarMakerCRUD makerService;

    @Autowired
    public CarModelCRUD(CarModelRepository modelRepository, CarMakerCRUD makerService) {
        this.modelRepository = modelRepository;
        this.makerService = makerService;
    }

    @Override
    public Iterable<Model> getAll() {
        return null;
    }

    @Override
    public Iterable<Model> getAllWithReferenceID(Integer integer) {
        return null;
    }

    @Override
    public Model addOne(Model model) {
        if (modelRepository.findByName(model.getName()) == null) {
            String makerName = model.getManufacturer().getName();
            Maker maker = makerService.getByName(makerName);
            if (maker == null) {
                maker = new Maker();
                maker.setName(makerName);
                maker = makerService.addOne(maker);
            }
            model.setManufacturer(maker);
            Set<Model> makersModels = maker.getModels();
            if (makersModels == null) {
                makersModels = new HashSet<>();
            }
            makersModels.add(model);
            maker.setModels(makersModels);
            return modelRepository.save(model);
        } else {
            return null;
        }
    }

    @Override
    public Model updateOne(Model model) {
        return null;
    }

    @Override
    public Model deleteOne(Model model) {
        return null;
    }
}
