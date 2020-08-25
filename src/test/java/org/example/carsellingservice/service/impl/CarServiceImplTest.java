package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.dto.CarDto;
import org.example.carsellingservice.service.api.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarServiceImplTest {

    @Autowired
    private CarService service;

    @Test
    public void whenGetAllThenResultIsNotEmpty() {
        List<CarDto> cars = service.getCars(
                null, null, null,
                null, null, null,
                null, null);

        assertFalse(cars.isEmpty());
    }

    @Test
    public void whenFindCarsThenResultIsNotEmpty() {
        List<CarDto> cars = service.getCars(
                1,
                1L,
                200000,
                500000,
                1960,
                2020,
                List.of(Transmission.AUTOMATIC, Transmission.MECHANIC),
                List.of(EngineType.GASOLINE, EngineType.DIESEL, EngineType.HYBRID, EngineType.ELECTRO)
        );

        assertFalse(cars.isEmpty());
    }

    @Test
    public void whenGetCarsOfUserWithIdThenResultIsNotEmpty() {
        List<CarDto> cars = service.getCarsOfUserWithId(1L);

        assertFalse(cars.isEmpty());
    }
}
