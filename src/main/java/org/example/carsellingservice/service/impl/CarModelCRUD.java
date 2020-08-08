package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Maker;
import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarModelCRUD implements CarModelService {

    private final CarModelRepository modelRepository;
    private final CarMakerRepository makerRepository;

    @Autowired
    public CarModelCRUD(CarModelRepository modelRepository, CarMakerRepository makerRepository) {
        this.modelRepository = modelRepository;
        this.makerRepository = makerRepository;
    }

    @Override
    public Iterable<Model> getAll() {
        return null;
    }

    @Override
    public Model addOne(Model model) {
        if (modelRepository.findByName(model.getName()) == null) {
            String makerName = model.getMaker().getName();
            Maker maker = makerRepository.findByName(makerName);
            if (maker == null) {
                maker = new Maker();
                maker.setName(makerName);
                maker = makerRepository.save(maker);
            }
            model.setMaker(maker);
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
    public Iterable<Model> getAllModelsOfMaker(String makerName) {
        return makerRepository.findByName(makerName).getModels();
    }

    @Override
    public void updateOne(Model previousModel, Model model) {
        previousModel = modelRepository.findByName(previousModel.getName());
        if (previousModel != null) {
            previousModel.setName(model.getName());
            Maker maker = makerRepository.findByName(previousModel.getMaker().getName());
            if (maker != null) {
                maker.setName(model.getMaker().getName());
                makerRepository.save(maker);
                previousModel.setMaker(maker);
                modelRepository.save(previousModel);
            }
        }
    }

    @Override
    public void deleteOne(@RequestBody Model model) {
        Model modelFromDatabase = modelRepository.findByName(model.getName());
        if (modelFromDatabase != null) {
            modelRepository.delete(modelFromDatabase);
            Maker maker = makerRepository.findByName(model.getMaker().getName());
            if (maker != null) {
                Set<Model> makersModels = maker.getModels();
                makersModels.remove(model);
                if (makersModels.size() == 0) {
                    makerRepository.delete(maker);
                } else {
                    maker.setModels(makersModels);
                    makerRepository.save(maker);
                }
            }
        }
    }
}
