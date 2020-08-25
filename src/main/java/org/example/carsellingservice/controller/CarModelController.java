package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class CarModelController {

    private final CarModelService modelService;

    @GetMapping
    public Iterable<CarModel> getModelsOfMaker(@RequestParam String makerName) {
        return modelService.getAllModelsOfMaker(makerName);
    }

    @PostMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public CarModel add(@RequestBody CarModel model) {
        return modelService.addOne(model);
    }

    //todo роли
    @PutMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void update(@RequestBody CarModel[] models) {
        modelService.updateOne(models[0], models[1]);
    }

    //todo роли
    @DeleteMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void delete(@RequestBody CarModel model) {
        modelService.deleteOne(model);
    }
}
