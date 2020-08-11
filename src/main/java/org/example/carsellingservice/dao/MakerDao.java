package org.example.carsellingservice.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Класс доступа к данным. Возвращает марку автомобиля без ссылок на модели.
 */
@Data
@RequiredArgsConstructor
public class MakerDao {
    /**
     * Идентификатор.
     */
    private final int id;
    /**
     * Название марки автомобиля.
     */
    private final String name;
}
