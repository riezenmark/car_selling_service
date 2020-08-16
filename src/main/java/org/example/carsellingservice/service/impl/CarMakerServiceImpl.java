package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.dto.CarMakerDto;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.example.carsellingservice.service.util.CarMakerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
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
        List<CarMakerDto> result = new LinkedList<>();
        Optional.ofNullable(searchQuery).ifPresentOrElse(
                (makerName) -> result.addAll(mapper.map(makerRepository.findAllByNameLike(makerName.toUpperCase()))),
                () -> result.addAll(mapper.map(makerRepository.findAll()))
        );
        result.sort(Comparator.comparing(CarMakerDto::getName));
        return result;
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
                makerRepository
                        .findByName(maker.getName())
                        .orElseGet(() -> {
                            maker.setId(null);
                            maker.setModels(null);
                            return makerRepository.save(maker);
                        })
        );
    }

    //todo dto?
    //todo обработать exception при существующем имени/criteria?
    @Override
    public void update(Integer id, CarMaker maker) {
        makerRepository
                .findById(id)
                .ifPresent(makerFromRepository -> {
                    makerFromRepository.setName(maker.getName());
                    makerRepository.save(makerFromRepository);
                });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        makerRepository.findById(id).ifPresent(makerRepository::delete);
    }
}
