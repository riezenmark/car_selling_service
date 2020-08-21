package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.dto.CarDto;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarDto> searchForCars(
            @RequestParam(required = false) Integer manufacturer,
            @RequestParam(required = false) Long model,
            @RequestParam(required = false) Integer priceFrom,
            @RequestParam(required = false) Integer priceTo,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(value = "transmission[]", required = false) List<Transmission> transmission,
            @RequestParam(value = "engineType[]", required = false) List<EngineType> engineType
    ) {
        return carService.getCars(
                manufacturer, model, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType
        );
    }

    @GetMapping("{id}")
    public CarDto get(@PathVariable final Long id) {
        return carService.getById(id);
    }

    @PostMapping
    public CarDto save(@RequestBody final Car car) {
        return carService.add(car);
    }

    @PutMapping("{id}")
    public CarDto update(@PathVariable Long id, @RequestBody final Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
