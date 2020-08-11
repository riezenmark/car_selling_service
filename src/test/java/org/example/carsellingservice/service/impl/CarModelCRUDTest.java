package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Model;
import org.example.carsellingservice.service.api.CarModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarModelCRUDTest {

    @Autowired
    private CarModelService service;

    @Test
    public void whenAddExistingModelThenResultIsNull() {
        Model model = new Model();
        model.setName("CR-V");

        Model result = service.addOne(model);

        assertNull(result);
    }

    @Test
    public void whenGetAllModelsOfMakerWithNameThenResultIsIterableOfModels() {
        List<Model> models = StreamSupport.stream(
                service.getAllModelsOfMaker("Honda").spliterator(),
                false
        ).collect(Collectors.toList());
        assertFalse(models.isEmpty());
    }
}