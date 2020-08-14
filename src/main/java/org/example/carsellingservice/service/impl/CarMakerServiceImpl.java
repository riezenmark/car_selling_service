package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarMakerServiceImpl implements CarMakerService {

    private final CarMakerRepository makerRepository;

    //todo transactional
    //todo page
    //todo criteria
    @Override
    public List<CarMaker> getMakers(String searchQuery) {
        if (searchQuery != null && !searchQuery.equals("")) {
            return makerRepository.findAllByNameLike(searchQuery.toUpperCase());
        } else {
            return makerRepository.findAll();
        }
    }

    @Override
    public CarMaker getById(Integer id) {
        return makerRepository.findById(id).orElse(null);
    }

    //todo optional?
    //todo criteria?
    //todo dto?
    @Override
    public CarMaker add(CarMaker maker) {
        return makerRepository
                .findByName(maker.getName())
                .orElseGet(() -> {
                    maker.setId(null);
                    return makerRepository.save(maker);
                });
    }

    //todo dto?
    //todo обработать exception при существующем имени
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
    public void delete(Integer id) {
        makerRepository.findById(id).ifPresent(makerRepository::delete);
    }
}
