package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Model;

/**
 * Сервис для работы с моделями машин.
 */
public interface CarModelService {

    /**
     * Сохраняет новую модель машины.
     * @param model - модель машины.
     * @return Результат сохранения.
     */
    Model addOne(Model model);

    /**
     * Возвращает все модели производителя.
     * @param makerName - название марки машины (производителя).
     * @return Возвращенные модели.
     */
    Iterable<Model> getAllModelsOfMaker(String makerName);

    /**
     * Обновляет поля модели машины.
     * @param previousModel - неизменённая модель.
     * @param model - модель с полями для замены их в другой модели.
     */
    void updateOne(Model previousModel, Model model);

    /**
     * Удаляет модель.
     * @param model - модель для удаления.
     */
    void deleteOne(Model model);
}
