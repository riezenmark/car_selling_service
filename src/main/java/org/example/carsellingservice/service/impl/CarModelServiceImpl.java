package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.example.carsellingservice.repository.specification.CarModelSpecification.*;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository modelRepository;
    private final CarMakerService makerService;

    @Override
    @Transactional(readOnly = true)
    public List<CarModel> getModels(final String searchQuery, final Integer makerId) {
        List<CarModel> modelsFromDatabase;
        if (makerId == null) {
            modelsFromDatabase = Optional.ofNullable(searchQuery)
                    .map(name -> modelRepository.findAll(carModelsWithNameLike(name)))
                    .orElseGet(modelRepository::findAll);
        } else {
            modelsFromDatabase = Optional.ofNullable(searchQuery)
                    .map(name -> modelRepository.findAll(carModelsOfMakerWithIdAndNameLike(makerId, name)))
                    .orElseGet(() -> modelRepository.findAll(carModelsOfCarMakerWithId(makerId)));
        }
        return modelsFromDatabase;
    }

    @Override
    @Transactional(readOnly = true)
    public CarModel getById(final Long id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public CarModel add(final CarModel model) {
        CarModel modelFromRepository = null;
        if (modelFieldsAreValid(model) && makerService.existsById(model.getMaker().getId())) {
            modelFromRepository = modelRepository
                    .findByNameAndMaker_id(model.getName(), model.getMaker().getId())
                    .orElseGet(() -> {
                        model.setId(null);
                        return modelRepository.save(model);
                    });
        }
        return modelFromRepository;
    }

    @Override
    @Transactional
    public CarModel update(final Long id, final CarModel model) {
        return modelRepository.findById(id)
                .filter(modelFromRepository -> !makerService.carMakerOfModelAlreadyHasModelWithName(modelFromRepository, model.getName()))
                .map(modelFromRepository -> {
                    modelFromRepository.setName(model.getName());
                    return modelRepository.save(modelFromRepository);
                })
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        modelRepository.findById(id).ifPresent(modelRepository::delete);
    }

    private boolean modelFieldsAreValid(final CarModel model) {
        return model.getName() != null
                && model.getMaker() != null
                && model.getMaker().getId() != null;
    }
}
