package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с пользователями.
 */
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class UserController {

    /**
     * Сервис для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Находит пользователей с соответствующими запросу именем без учёта регистра
     * или всех пользователей, если строка запроса пуста.
     * @param searchQuery - запрос для поиска.
     * @return Возвращенные пользователи.
     */
    @GetMapping
    public List<User> list(@RequestParam(name = "q", required = false) String searchQuery) {
        return userService.getUsers(searchQuery);
    }

    /**
     * Возвращает пользователя по id.
     * @param id - id пользователя.
     * @return Найденный пользователь или {@code null}.
     */
    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * Изменяет пользователя по id.
     * @param id - id пользователя для изменеия.
     * @param user - пользователь с новыми значениями полей.
     * @return Результат изменения.
     */
    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /**
     * Удаляет пользователя по id.
     * @param id - id пользователя.
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
