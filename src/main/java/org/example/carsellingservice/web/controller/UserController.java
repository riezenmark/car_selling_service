package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.example.carsellingservice.web.exception.NotFoundException;
import org.example.carsellingservice.web.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//todo добавить поиск ignorecase
//todo добавить (проверить) запросы извне (csrf?)
//todo передача неполного json-а (json view)
@RestController
@RequestMapping("users")
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
    public User getOne(@PathVariable("id") Long id) {
        User returnedUser = service.getById(id);
        if (returnedUser == null) {
            throw new NotFoundException("User with id " + id + " not found.");
        } else {
            return returnedUser;
        }
    }

    @PostMapping
    public User createNewOne(@RequestBody User user) {
        User returnedUser = service.add(user);
        if (returnedUser == null) {
            throw new UnprocessableEntityException("There is already a user with the given id.");
        } else {
            return returnedUser;
        }
    }

    //todo убрать передачу пароля из базы
    //todo проверить на затирание id при передаче его в теле json-а (+ при отсутсвии копирования)
    //todo проверить на 404 при несуществующем пользователе
    @PutMapping("{id}")
    public User updateOne(
            @PathVariable("id") Long id,
            @RequestBody User user
    ) {
        return service.updateById(id, user);
    }

    //todo проверить удаление несуществующего и тд
    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    //todo error page + разнести исключения если требуется
    /*
    private User checkUserForException(User returnedUser, String message) {
        if (returnedUser == null) {

        }
    }     */
}
