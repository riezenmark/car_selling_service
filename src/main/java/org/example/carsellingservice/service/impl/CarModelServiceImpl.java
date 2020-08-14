package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.dto.CarModelDto;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.example.carsellingservice.service.util.CarModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository modelRepository;
    private final CarModelMapper mapper;

    //todo transactional
    //todo page
    //todo criteria
    @Override
    public List<CarModelDto> getModels(String searchQuery, Integer makerId) {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return mapper.map(modelRepository.findAllByNameLike(searchQuery.toUpperCase()));
        } else {
            return mapper.map(modelRepository.findAll());
        }
    }

    @Override
    public CarModel getById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public CarModel add(CarModel model) {
        return modelRepository
                .findByNameAndMaker_id(model.getName(), model.getMaker().getId())
                .orElseGet(() -> {
                    model.setId(null);
                    return modelRepository.save(model);
                });
    }

    //todo вернуть результат
    @Override
    public void update(Long id, CarModel model) {
        modelRepository
                .findById(id)
                .ifPresent(modelFromRepository -> {
                    modelFromRepository.setName(model.getName());
                    modelRepository.save(model);
                });
    }

    @Override
    public void delete(Long id) {
        modelRepository.findById(id).ifPresent(modelRepository::delete);
    }
}
