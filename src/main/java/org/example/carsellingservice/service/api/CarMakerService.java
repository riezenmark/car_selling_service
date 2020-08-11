package org.example.carsellingservice.service.api;

import org.example.carsellingservice.dao.MakerDao;

/**
 * Сервис для работы с марками машин.
 */
public interface CarMakerService {
    /**
     * Возвращает все марки машин машин без моделей.
     * @return Возвращённые марки машин.
     */
    Iterable<MakerDao> getAllWithoutModels();
}
