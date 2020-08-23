package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registration() {
        return "signup";
    }

    @PostMapping
    public String addUser(final User user, final Map<String, Object> model) {
        String page = "signup";
        if (userService.userWithNameExists(user)) {
            model.put("message", "User with name " + user.getUsername() + " already exists");
        } else {
            userService.addNew(user);
            page = "redirect:/login";
        }
        return page;
    }
}
