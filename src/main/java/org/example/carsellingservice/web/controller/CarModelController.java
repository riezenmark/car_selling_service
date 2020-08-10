package org.example.carsellingservice.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.domain.view.Views;
import org.example.carsellingservice.service.impl.CarModelCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/models")
public class CarModelController {

    private final CarModelCRUD modelService;

    @Autowired
    public CarModelController(CarModelCRUD modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public Iterable<Model> getModelsOfMaker(@RequestParam String makerName) {
        return modelService.getAllModelsOfMaker(makerName);
    }

    @PostMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @JsonView(Views.IdName.class)
    public Model addNewModel(@RequestBody Model model) {
        return modelService.addOne(model);
    }

    @PutMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void updateModel(@RequestBody Model[] models) {
        modelService.updateOne(models[0], models[1]);
    }

    @DeleteMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void deleteModel(@RequestBody Model model) {
        modelService.deleteOne(model);
    }
}
