package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.example.carsellingservice.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//todo добавить поиск (ignorecase, %)
//todo добавить (проверить) запросы извне (csrf?)
//todo передача неполного json-а (json view)
@RestController
@RequestMapping("users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<User> list() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") String id) {
        User returnedUser = service.getById(id);
        if (returnedUser == null) {
            throw new NotFoundException("User with id " + id + " not found.");
        } else {
            return returnedUser;
        }
    }

    //todo проверить удаление несуществующего и тд
    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    //todo error page + разнести исключения если требуется
}
