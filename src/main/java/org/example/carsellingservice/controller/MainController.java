package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Car;
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
@RequiredArgsConstructor
public class MainController {

    //todo ?
    private final CarMakerService makerService;
    private final CarService carService;

    //todo убрать девмод
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> frontendData = new HashMap<>();
        if (user != null) {
            frontendData.put("profile", user);
            Iterable<Car> addedCars = carService.getCarsOfUserWithId(user.getId());
            frontendData.put("addedCars", addedCars);
        }
        frontendData.put("makers", makerService.getAllWithoutModels());
        frontendData.put("maximumPrice", carService.getMaximumCarPrice());
        model.addAttribute("frontendData", frontendData);
        return "index";
    }
}
