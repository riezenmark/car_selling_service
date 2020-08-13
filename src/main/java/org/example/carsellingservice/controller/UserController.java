package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo добавить (проверить) запросы извне (csrf?)
//todo передача неполного json-а (json view)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users")
    @GetMapping
    public List<User> list(@RequestParam(required = false) String q) {
        return service.getWithoutCars(q);
    }

    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @RequestMapping("/admin/users/{id}")
    @DeleteMapping
    public void deleteOne(@PathVariable("id") String id) {
        service.deleteById(id);
    }
}
