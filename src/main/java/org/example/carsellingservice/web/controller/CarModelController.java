package org.example.carsellingservice.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.domain.view.Views;
import org.example.carsellingservice.service.impl.CarModelCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для моделей машин.
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/models")
public class CarModelController {

    /**
     * Сервис для работы с моделями машин.
     */
    private final CarModelCRUD modelService;

    @Autowired
    public CarModelController(CarModelCRUD modelService) {
        this.modelService = modelService;
    }

    /**
     * Возвращает все модели производителя.
     * @param makerName - название марки машины (производителя).
     * @return Возвращенные модели.
     */
    @GetMapping
    @JsonView(Views.IdName.class)
    public Iterable<Model> getModelsOfMaker(@RequestParam String makerName) {
        return modelService.getAllModelsOfMaker(makerName);
    }

    /**
     * Сохраняет новую модель машины.
     * @param model - модель машины.
     * @return Результат сохранения.
     */
    @PostMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    @JsonView(Views.IdName.class)
    public Model addNewModel(@RequestBody Model model) {
        return modelService.addOne(model);
    }

    /**
     * Обновляет поля модели машины.
     * @param previousModel - неизменённая модель.
     * @param models - массив из модели с полями для замены и модели с изменёными полями.
     */
    @PutMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void updateModel(@RequestBody Model[] models) {
        modelService.updateOne(models[0], models[1]);
    }

    /**
     * Удаляет модель.
     * @param model - модель для удаления.
     */
    @DeleteMapping
    @PreAuthorize("principal.email == 'riezenmark@gmail.com'")
    public void deleteModel(@RequestBody Model model) {
        modelService.deleteOne(model);
    }
}
