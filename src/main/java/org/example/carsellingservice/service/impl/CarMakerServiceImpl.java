package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.dto.CarMakerDto;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.example.carsellingservice.service.util.CarMakerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarMakerServiceImpl implements CarMakerService {

    private final CarMakerRepository makerRepository;
    private final CarMakerMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarMakerDto> getMakers(String searchQuery) {
        return mapper.map(
                Optional.ofNullable(searchQuery)
                        .map(makerName -> makerRepository.findAllByNameLike(makerName.toUpperCase()))
                        .orElseGet(makerRepository::findAll)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CarMaker getById(Integer id) {
        return makerRepository.findByIdWithModels(id).orElse(null);
    }

    @Override
    @Transactional
    public CarMakerDto add(CarMaker maker) {
        return mapper.map(
                Optional.ofNullable(maker.getName())
                        .map(name -> makerRepository.findByName(name).orElseGet(() -> {
                            maker.setId(null);
                            maker.setModels(null);
                            return makerRepository.save(maker);
                        })
                        ).orElse(null)
        );
    }

    @Override
    @Transactional
    public CarMakerDto update(Integer id, CarMaker maker) {
        CarMaker makerFromDatabase = makerRepository.findByName(maker.getName()).orElse(null);
        if (makerFromDatabase == null) {
            makerFromDatabase = makerRepository.findById(id).orElse(null);
            if (makerFromDatabase != null) {
                makerFromDatabase.setName(maker.getName());
                makerRepository.save(makerFromDatabase);
            }
        }
        return mapper.map(makerFromDatabase);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        makerRepository.findById(id).ifPresent(makerRepository::cascadeDelete);
    }
}
