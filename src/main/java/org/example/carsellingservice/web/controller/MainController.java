package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    //todo получить пользователя из базы (bean в security config?) (authPrincipal?)
    //todo получить список машин пользователя
    //todo hashMap <string> ?
    //todo profile
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> userData = new HashMap<>();
        userData.put("profile", user);
        userData.put("cars", null);
        model.addAttribute("userData", userData);
        model.addAttribute("isDevMode", true);
        return "index";
    }
}
