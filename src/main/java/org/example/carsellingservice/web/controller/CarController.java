package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /*@GetMapping
    public Iterable<Car> searchForCars(
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) int yearOfProduction,
            @RequestParam(required = false) Transmission transmission,
            @RequestParam(required = false) EngineType engineType,
            @RequestParam(required = false) float engineCapacity
    ) {
        return carService.findCars(
                manufacturer, model, price, yearOfProduction,
                transmission, engineType, engineCapacity
        );
    }*/

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
