package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.service.api.CarModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarModelServiceImplTest {

    @Autowired
    private CarModelService service;

    @Test
    public void whenAddExistingModelThenResultIsNull() {
        CarModel model = new CarModel();
        model.setName("CR-V");

        CarModel result = service.add(model);

        assertNull(result);
    }

    @Test
    public void whenGetAllModelsOfMakerWithNameThenResultIsListOfModels() {
        List<CarModel> models = service.getModels(null, 1);
        assertFalse(models.isEmpty());
    }
}