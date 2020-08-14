package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //todo role restrict
    //todo page
    @GetMapping
    public List<User> list(@RequestParam(name = "q", required = false) String searchQuery) {
        return userService.getUsers(searchQuery);
    }

    @GetMapping("{id}")
    public User get(@PathVariable String id) {
        return userService.getById(id);
    }

    //todo role restrict
    //todo page
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }
}
