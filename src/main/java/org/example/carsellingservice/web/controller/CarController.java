package org.example.carsellingservice.web.controller;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Контроллер для машин.
 */
@RestController
@RequestMapping("cars")
public class CarController {

    /**
     * Сервис для работы с машинами.
     */
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Поиск машин по набору параметров.
     * @param manufacturer - название марки машины.
     * @param model - название модели машины.
     * @param priceFrom - нижний ценовой диапазон.
     * @param priceTo - верхний ценовой диапазон.
     * @param yearFrom - нижний диапазон года производсва.
     * @param yearTo - верхний диапазон года производсва.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @return Найденные машны.
     */
    @GetMapping
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

    /**
     * Возвращает машины пользователя с заданным id.
     * @param id - id пользователя.
     * @return Возвращенные машины.
     */
    @GetMapping("{id}")
    public Iterable<Car> getCarsOfUser(@PathVariable String id) {
        return carService.getCarsOfUserWithId(id);
    }

    /**
     * Добавляет новую машину.
     * @param user - пользователь, добавивший машину.
     * @param modelName - названи модели машины.
     * @param makerName - название марки машины.
     * @param price - цены машины.
     * @param yearOfProduction - год производства машины.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @param file - фото машины.
     */
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

    /**
     * Обновляет поля машины, добавленной пользователем.
     * @param car - машина.
     * @param user - пользователь, добавивший машину.
     */
    @PutMapping
    public void updateCar(@RequestBody Car car, @AuthenticationPrincipal User user) {
        carService.updateCar(car, user);
    }

    /**
     * Удаляет машину, добавленную пользователем.
     * @param id - id машины.
     * @param user - пользователь, добавивший машину.
     */
    @DeleteMapping
    public void deleteCarWithId(@RequestParam Long id, @AuthenticationPrincipal User user) {
        carService.deleteCarWithId(id, user);
    }
}
