package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    //todo Автовайрим сервисы, передаём дто (mapStruct)
    private final CarModelRepository modelRepository;
    private final CarMakerRepository makerRepository;

    public CarModel addOne(CarModel model) {
        CarModel resultingModel = null;
        //todo использовать опшионалы
        if (modelRepository.findByName(model.getName()) == null) {
            String makerName = model.getCarMaker().getName();
            CarMaker carMaker = makerRepository.findByName(makerName);
            if (carMaker == null) {
                carMaker = this.createNewMakerWithName(makerName);
            }
            model.setCarMaker(carMaker);
            Set<CarModel> makersModels = this.getModelsOfMaker(carMaker);
            makersModels.add(model);
            carMaker.setModels(makersModels);
            resultingModel =  modelRepository.save(model);
        }
        return resultingModel;
    }

    @Override
    public Iterable<CarModel> getAllModelsOfMaker(String makerName) {
        return makerRepository.findByName(makerName).getModels();
    }

    @Override
    public void updateOne(CarModel previousModel, CarModel model) {
        //todo использовать опшионалы
        previousModel = modelRepository.findByName(previousModel.getName());
        if (previousModel != null) {
            previousModel.setName(model.getName());
            CarMaker carMaker = makerRepository.findByName(previousModel.getCarMaker().getName());
            if (carMaker != null) {
                String makerName = model.getCarMaker().getName();
                this.saveModelAndMakerWithName(carMaker, makerName, previousModel);
            }
        }
    }

    @Override
    public void deleteOne(CarModel model) {
        //todo использовать опшионалы
        CarModel modelFromDatabase = modelRepository.findByName(model.getName());
        if (modelFromDatabase != null) {
            modelRepository.delete(modelFromDatabase);
            CarMaker carMaker = makerRepository.findByName(model.getCarMaker().getName());
            if (carMaker != null) {
                Set<CarModel> makersModels = this.removeModelFromMaker(carMaker, model);
                this.deleteMakerOrSaveMakersModels(carMaker, makersModels);
            }
        }
    }

    private CarMaker createNewMakerWithName(String makerName) {
        CarMaker carMaker = new CarMaker();
        carMaker.setName(makerName);
        return makerRepository.save(carMaker);
    }

    private Set<CarModel> getModelsOfMaker(CarMaker carMaker) {
        Set<CarModel> makersModels = carMaker.getModels();
        if (makersModels == null) {
            makersModels = new HashSet<>();
        }
        return makersModels;
    }

    private void saveModelAndMakerWithName(CarMaker carMaker, String makerName, CarModel previousModel) {
        carMaker.setName(makerName);
        makerRepository.save(carMaker);
        previousModel.setCarMaker(carMaker);
        modelRepository.save(previousModel);
    }

    private Set<CarModel> removeModelFromMaker(CarMaker carMaker, CarModel model) {
        Set<CarModel> makersModels = carMaker.getModels();
        makersModels.remove(model);
        return makersModels;
    }

    private void deleteMakerOrSaveMakersModels(CarMaker carMaker, Set<CarModel> makersModels) {
        if (makersModels.size() == 0) {
            makerRepository.delete(carMaker);
        } else {
            carMaker.setModels(makersModels);
            makerRepository.save(carMaker);
        }
    }
}
