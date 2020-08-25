package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.dto.CarDto;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для машин.
 */
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    /**
     * Сервис для работы с машинами.
     */
    private final CarService carService;

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
    public List<CarDto> searchForCars(
            @RequestParam(required = false) final Integer manufacturer,
            @RequestParam(required = false) final Long model,
            @RequestParam(required = false) final Integer priceFrom,
            @RequestParam(required = false) final Integer priceTo,
            @RequestParam(required = false) final Integer yearFrom,
            @RequestParam(required = false) final Integer yearTo,
            @RequestParam(value = "transmission[]", required = false) final List<Transmission> transmission,
            @RequestParam(value = "engineType[]", required = false) final List<EngineType> engineType
    ) {
        return carService.getCars(
                manufacturer, model, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType
        );
    }

    /**
     * Возвращает машину с заданным id.
     * @param id - id машины.
     * @return Возвращенная машина.
     */
    @GetMapping("{id}")
    public CarDto get(@PathVariable final Long id) {
        return carService.getById(id);
    }

    /**
     * Добавляет новую машину.
     * @param user - пользователь, добавивший машину.
     * @param car - машина.
     * @return Результат добавления.
     */
    @PostMapping
    public CarDto save(@RequestBody final Car car, @AuthenticationPrincipal final User user) {
        return carService.add(car, user);
    }

    /**
     * Обновляет поля машины, добавленной пользователем.
     * @param id - id изменяемой машины.
     * @param car - машина с полями для замены.
     * @param user - пользователь, добавивший машину.
     * @return Результат изменения.
     */
    @PutMapping("{id}")
    public CarDto update(@PathVariable final Long id, @RequestBody final Car car, @AuthenticationPrincipal final User user) {
        return carService.update(id, car, user);
    }

    /**
     * Удаляет машину, добавленную пользователем.
     * @param id - id машины.
     * @param user - пользователь, добавивший машину.
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id, @AuthenticationPrincipal final User user) {
        carService.deleteById(id, user);
    }
}
