package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.dao.MakerDao;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.service.api.CarMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация срвиса для работы с марками машин.
 */
@Service
public class CarMakerCRUD implements CarMakerService {

    /**
     * Хранилище марок машин.
     */
    private final CarMakerRepository makerRepository;

    @Autowired
    public CarMakerCRUD(CarMakerRepository makerRepository) {
        this.makerRepository = makerRepository;
    }

    /**
     * Возвращает все марки машин машин без моделей.
     * @return Возвращённые марки машин.
     */
    @Override
    public Iterable<MakerDao> getAllWithoutModels() {
        return makerRepository.getAllWithoutModels();
    }
}
