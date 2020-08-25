package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.service.api.CarModelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class CarModelController {

    private final CarModelService modelService;

    @GetMapping
    public List<CarModel> list(
            @RequestParam(name = "q", required = false) final String name,
            @RequestParam(name = "maker", required = false) final Integer makerId
    ) {
        return modelService.getModels(name, makerId);
    }

    @GetMapping("{id}")
    public CarModel get(@PathVariable final Long id) {
        return modelService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CarModel save(@RequestBody final CarModel model) {
        return modelService.add(model);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CarModel update(@PathVariable final Long id, @RequestBody final CarModel model) {
        return modelService.update(id, model);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable final Long id) {
        modelService.deleteById(id);
    }
}
