package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> list(@RequestParam(name = "q", required = false) String searchQuery) {
        return userService.getUsers(searchQuery);
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
