/*
package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.*;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public Iterable<Car> searchForCars(
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) boolean used,
            @RequestParam(required = false) int yearOfProduction,
            @RequestParam(required = false) Transmission transmission,
            @RequestParam(required = false) BodyworkType bodyworkType,
            @RequestParam(required = false) EngineType engineType,
            @RequestParam(required = false) float engineCapacity,
            @RequestParam(required = false) int mileage
    ) {
        return carService.findCars(
                manufacturer, model, price, used, yearOfProduction,
                transmission, bodyworkType, engineType, engineCapacity, mileage
        );
    }

    @PostMapping
    public Car addNewCar(
            @RequestParam User user,
            @RequestParam String manufacturer,
            @RequestParam String model,
            @RequestParam double price,
            @RequestParam boolean used,
            @RequestParam(required = false) int yearOfProduction,
            @RequestParam(required = false) Transmission transmission,
            @RequestParam(required = false) BodyworkType bodyworkType,
            @RequestParam(required = false) EngineType engineType,
            @RequestParam(required = false) float engineCapacity,
            @RequestParam(required = false) int mileage
    ) {
        return carService.addCarforUser(
                user,
                manufacturer, model, price, used, yearOfProduction,
                transmission, bodyworkType, engineType, engineCapacity, mileage
        );
    }

    @PostMapping
    public Car updateCar(
            @RequestParam User user,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) boolean used,
            @RequestParam(required = false) int yearOfProduction,
            @RequestParam(required = false) Transmission transmission,
            @RequestParam(required = false) BodyworkType bodyworkType,
            @RequestParam(required = false) EngineType engineType,
            @RequestParam(required = false) float engineCapacity,
            @RequestParam(required = false) int mileage
    ) {
        return carService.updateCarForUser(
                user,
                manufacturer, model, price, used, yearOfProduction,
                transmission, bodyworkType, engineType, engineCapacity, mileage
        );
    }

    @DeleteMapping
    public void deleteCar(@RequestParam User user, Car car) {
        carService.deleteChosenCarFromUser(user, car);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@RequestParam User user, @PathVariable Long carId) {
        carService.deleteChosenCarFromUser(user, carId);
    }
}
*/
