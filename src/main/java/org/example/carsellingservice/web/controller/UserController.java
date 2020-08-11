package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с пользователями.
 */
@SuppressWarnings("ALL")
@RestController
public class UserController {

    /**
     * Сервис для работы с пользователями.
     */
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Находит пользователей с соответствующими запросу именем или почтой без учёта регистра.
     * @param q - запрос для поиска.
     * @return Возвращенные пользователи.
     */
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users")
    @GetMapping
    public Iterable<UserDao> list(@RequestParam(required = false) String q) {
        return service.getWithoutCars(q);
    }

    /**
     * Удаляет пользователя по id.
     * @param id - id пользователя.
     */
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users/{id}")
    @DeleteMapping
    public void deleteOne(@PathVariable("id") String id) {
        service.deleteById(id);
    }
}
