package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.CarMakerService;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final CarMakerService makerService;
    private final CarService carService;

    @Autowired
    public MainController(CarMakerService makerService, CarService carService) {
        this.makerService = makerService;
        this.carService = carService;
    }

    //todo убрать девмод
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> frontendData = new HashMap<>();
        if (user != null) {
            frontendData.put("profile", user);
        }
        frontendData.put("makers", makerService.getAllWithoutModels());
        frontendData.put("maximumPrice", carService.getMaximumCarPrice());
        model.addAttribute("frontendData", frontendData);
        model.addAttribute("isDevMode", true);
        return "index";
    }
}
