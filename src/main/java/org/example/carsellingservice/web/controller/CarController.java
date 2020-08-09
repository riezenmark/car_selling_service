package org.example.carsellingservice.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.domain.view.Views;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @JsonView(Views.IdNameEmail.class)
    public Iterable<Car> getAllCars() {
        return carService.getAll();
    }

    @GetMapping("search")
    public Iterable<Car> searchForCars(
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer priceFrom,
            @RequestParam(required = false) Integer priceTo,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(value = "transmission[]", required = false) List<String> transmission,
            @RequestParam(value = "engineType[]", required = false) List<String> engineType
    ) {
        return carService.findCars(
                manufacturer, model, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType
        );
    }

    @PostMapping
    public void addNewCar(
            @AuthenticationPrincipal User user,
            @RequestParam String makerName,
            @RequestParam String modelName,
            @RequestParam int price,
            @RequestParam int yearOfProduction,
            @RequestParam String transmission,
            @RequestParam String engineType,
            @RequestParam(name = "file", required = false) MultipartFile file
            ) throws IOException {
        carService.addCarForUser(
                user, modelName, makerName, price, yearOfProduction, transmission, engineType, file
        );
    }
}
