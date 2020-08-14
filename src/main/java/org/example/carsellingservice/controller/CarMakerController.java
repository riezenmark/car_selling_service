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

    //todo page
    @GetMapping
    public List<CarMakerDto> list(@RequestParam(name = "q", required = false) String name) {
        return makerService.getMakers(name);
    }

    @GetMapping("{id}")
    public CarMaker get(@PathVariable Integer id) {
        return makerService.getById(id);
    }

    @PostMapping
    public CarMakerDto save(@RequestBody CarMaker maker) {
        return makerService.add(maker);
    }

    //todo возвращать результат
    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody CarMaker maker) {
        makerService.update(id, maker);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        makerService.delete(id);
    }
}
