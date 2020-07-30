package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    //todo получить пользователя из базы (bean в security config?)
    //todo получить список машин пользователя
    public String main(Model model, User user) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", null);
        data.put("cars", null);
        model.addAttribute(new HashMap<>());
        model.addAttribute("isDevMode", true);
        return "index";
    }
}
