package org.example.carsellingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.CarMaker;
import org.example.carsellingservice.dto.CarMakerDto;
import org.example.carsellingservice.service.api.CarMakerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makers")
@RequiredArgsConstructor
public class CarMakerController {

    private final CarMakerService makerService;

    @GetMapping
    public List<CarMakerDto> list(@RequestParam(name = "q", required = false) final String name) {
        return makerService.getMakers(name);
    }

    @GetMapping("{id}")
    public CarMaker get(@PathVariable final Integer id) {
        return makerService.getById(id);
    }

    @PostMapping
    public CarMakerDto save(@RequestBody final CarMaker maker) {
        return makerService.add(maker);
    }

    @PutMapping("{id}")
    public CarMakerDto update(@PathVariable final Integer id, @RequestBody final CarMaker maker) {
        return makerService.update(id, maker);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final Integer id) {
        makerService.deleteById(id);
    }
}
