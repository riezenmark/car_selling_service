package org.example.carsellingservice.service.api;

import org.example.carsellingservice.dao.UserDao;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService {

    /**
     * Находит пользователей с соответствующими запросу именем или почтой без учёта регистра.
     * @param q - запрос для поиска.
     * @return Возвращенные пользователи.
     */
    Iterable<UserDao> getWithoutCars(String q);

    /**
     * Удаляет пользователя по id.
     * @param id - id пользователя.
     */
    void deleteById(String id);
}
