package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Maker;
import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация сервиса для работы с моделями машин.
 */
@Service
public class CarModelCRUD implements CarModelService {

    /**
     * Хранилище моделей машиню
     */
    private final CarModelRepository modelRepository;
    /**
     * Хранилище марок машин.
     */
    private final CarMakerRepository makerRepository;

    @Autowired
    public CarModelCRUD(CarModelRepository modelRepository, CarMakerRepository makerRepository) {
        this.modelRepository = modelRepository;
        this.makerRepository = makerRepository;
    }

    /**
     * Сохраняет новую модель машины.
     * @param model - модель машины.
     * @return Результат сохранения.
     */
    @Override
    public Model addOne(Model model) {
        Model resultingModel = null;
        if (modelRepository.findByName(model.getName()) == null) {
            String makerName = model.getMaker().getName();
            Maker maker = makerRepository.findByName(makerName);
            if (maker == null) {
                maker = this.createNewMakerWithName(makerName);
            }
            model.setMaker(maker);
            Set<Model> makersModels = this.getModelsOfMaker(maker);
            makersModels.add(model);
            maker.setModels(makersModels);
            resultingModel =  modelRepository.save(model);
        }
        return resultingModel;
    }

    /**
     * Возвращает все модели производителя.
     * @param makerName - название марки машины (производителя).
     * @return Возвращенные модели.
     */
    @Override
    public Iterable<Model> getAllModelsOfMaker(String makerName) {
        return makerRepository.findByName(makerName).getModels();
    }

    /**
     * Обновляет поля модели машины.
     * @param previousModel - неизменённая модель.
     * @param model - модель с полями для замены их в другой модели.
     */
    @Override
    public void updateOne(Model previousModel, Model model) {
        previousModel = modelRepository.findByName(previousModel.getName());
        if (previousModel != null) {
            previousModel.setName(model.getName());
            Maker maker = makerRepository.findByName(previousModel.getMaker().getName());
            if (maker != null) {
                String makerName = model.getMaker().getName();
                this.saveModelAndMakerWithName(maker, makerName, previousModel);
            }
        }
    }

    /**
     * Удаляет модель.
     * @param model - модель для удаления.
     */
    @Override
    public void deleteOne(Model model) {
        Model modelFromDatabase = modelRepository.findByName(model.getName());
        if (modelFromDatabase != null) {
            modelRepository.delete(modelFromDatabase);
            Maker maker = makerRepository.findByName(model.getMaker().getName());
            if (maker != null) {
                Set<Model> makersModels = this.removeModelFromMaker(maker, model);
                this.deleteMakerOrSaveMakersModels(maker, makersModels);
            }
        }
    }

    /**
     * Создаёт новую марку машины с заданным названием и сохраняет её в репозиторий.
     * @param makerName - название марки.
     * @return Результат сохранения новой марки в репозиторий.
     */
    private Maker createNewMakerWithName(String makerName) {
        Maker maker = new Maker();
        maker.setName(makerName);
        return makerRepository.save(maker);
    }

    /**
     * Возвращает список моделей производителя.
     * @param maker - производитель.
     * @return Список моделей производителя.
     */
    private Set<Model> getModelsOfMaker(Maker maker) {
        Set<Model> makersModels = maker.getModels();
        if (makersModels == null) {
            makersModels = new HashSet<>();
        }
        return makersModels;
    }

    private void saveModelAndMakerWithName(Maker maker, String makerName, Model previousModel) {
        maker.setName(makerName);
        makerRepository.save(maker);
        previousModel.setMaker(maker);
        modelRepository.save(previousModel);
    }

    /**
     * Удаляет модель из коллекции моделей производителя.
     * @param maker - производитель.
     * @param model - модель.
     * @return Сокращённая коллекция моделей производителя.
     */
    private Set<Model> removeModelFromMaker(Maker maker, Model model) {
        Set<Model> makersModels = maker.getModels();
        makersModels.remove(model);
        return makersModels;
    }

    /**
     * Удаляет производмтеля из репозитория, если у него больше нет моделей, или сохраняет новый список его моделей.
     * @param maker - производитель.
     * @param makersModels - модели машин производителя.
     */
    private void deleteMakerOrSaveMakersModels(Maker maker, Set<Model> makersModels) {
        if (makersModels.size() == 0) {
            makerRepository.delete(maker);
        } else {
            maker.setModels(makersModels);
            makerRepository.save(maker);
        }
    }
}
