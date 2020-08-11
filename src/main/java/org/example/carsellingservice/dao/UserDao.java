package org.example.carsellingservice.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс доступа к данным. Возвращает пользователя без ссылок на добавленные им машины.
 */
@Data
@RequiredArgsConstructor
public class UserDao {
    /**
     * Идентификатор.
     */
    private final String id;
    /**
     * Полное имя пользователя.
     */
    private final String name;
    /**
     * Ссылка на аватар в Google.
     */
    private final String userpic;
    /**
     * Электронная почта пользователя.
     */
    private final String email;
    /**
     * Пол пользователя.
     */
    private final String gender;
    /**
     * Локаль.
     */
    private final String locale;
    /**
     * Время последнего визита.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastVisit;
}
