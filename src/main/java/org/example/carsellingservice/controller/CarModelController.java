package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class CarModelController {

    private final CarModelService modelService;

    //todo page
    @GetMapping
    public List<CarModel> list(
            @RequestParam(name = "q", required = false) String name,
            @RequestParam(name = "maker", required = false) Integer makerId
    ) {
        return modelService.getModels(name, makerId);
    }

    @GetMapping("{id}")
    public CarModel get(@PathVariable Long id) {
        return modelService.getById(id);
    }

    @PostMapping
    public CarModel save(@RequestBody CarModel model) {
        return modelService.add(model);
    }

    //todo возвращать результат
    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody CarModel model) {
        modelService.update(id, model);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        modelService.delete(id);
    }
}
