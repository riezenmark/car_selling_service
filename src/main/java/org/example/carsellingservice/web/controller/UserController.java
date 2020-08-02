package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//todo добавить (проверить) запросы извне (csrf?)
//todo передача неполного json-а (json view)
@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users")
    @GetMapping
    public Iterable<UserDao> list(@RequestParam(required = false) String q) {
        return service.getWithoutCars(q);
    }

    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users/{id}")
    @DeleteMapping
    public void deleteOne(@PathVariable("id") String id) {
        service.deleteById(id);
    }
}
