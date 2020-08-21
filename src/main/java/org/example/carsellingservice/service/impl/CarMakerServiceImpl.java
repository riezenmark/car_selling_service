package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.domain.CarModel;
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
    public List<CarMakerDto> getMakers(final String searchQuery) {
        return mapper.map(
                Optional.ofNullable(searchQuery)
                        .map(makerName -> makerRepository.findAllByNameLike(makerName.toUpperCase()))
                        .orElseGet(makerRepository::findAll)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CarMaker getById(final Integer id) {
        return makerRepository.findByIdWithModels(id).orElse(null);
    }

    @Override
    @Transactional
    public CarMakerDto add(final CarMaker maker) {
        return mapper.map(
                Optional.ofNullable(maker.getName())
                        .map(name -> makerRepository.findByName(name).orElseGet(() -> {
                            maker.setId(null);
                            maker.setModels(null);
                            return makerRepository.save(maker);
                        }))
                        .orElse(null)
        );
    }

    @Override
    @Transactional
    public CarMakerDto update(final Integer id, final CarMaker maker) {
        CarMaker makerFromRepository = makerRepository.findByName(maker.getName()).orElse(null);
        if (makerFromRepository == null) {
            makerFromRepository = makerRepository.findById(id).orElse(null);
            if (makerFromRepository != null) {
                makerFromRepository.setName(maker.getName());
                makerRepository.save(makerFromRepository);
            }
        }
        return mapper.map(makerFromRepository);
    }

    @Override
    @Transactional
    public void deleteById(final Integer id) {
        makerRepository.findById(id).ifPresent(makerRepository::bulkDeleteCascade);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(final Integer id) {
        return makerRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean carMakerOfModelHasModelWithName(final CarModel model, final String name) {
        return Optional.ofNullable(name)
                .map(s -> getById(model.getMaker().getId()).getModels().stream().anyMatch(m -> m.getName().equals(name)))
                .orElse(true);
    }
}
