package org.example.carsellingservice.web.controller;

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

/**
 * Контроллер для главной страницы приложения.
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * Сервис для работы с марками машин.
     */
    private final CarMakerService makerService;
    /**
     * Сервис для работы с машинами.
     */
    private final CarService carService;

    @Autowired
    public MainController(CarMakerService makerService, CarService carService) {
        this.makerService = makerService;
        this.carService = carService;
    }

    /**
     * Передаёт список моделей, максимальную цену машины (для сокращения количества запросов к базе)
     * и данные пользователя, если он авторизован (для изменения вида и набора разрешённых действий
     * в зависимости от роли пользователя), для хранения на клиенте.
     * @param model - модель данных для передачи на клиент.
     * @param user - пользователь, авторизованный в данный момент.
     * @return Главная страница приложения.
     */
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
