package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.service.api.CarService;
import org.example.carsellingservice.service.api.UserService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CarCRUDTest {

    @Autowired
    private CarService service;

    @Autowired
    private UserService userService;

    @Test
    public void whenGetAllThenResultIsNotEmpty() {
        List<Car> cars = StreamSupport.stream(
                service.getAll().spliterator(),
                false
        ).collect(Collectors.toList());

        assertFalse(cars.isEmpty());
    }

    @Test
    public void whenFindCarsThenResultIsNotEmpty() {
        List<Car> cars = StreamSupport.stream(
                service.findCars(
                        "Honda",
                        "CR-V",
                        500000,
                        200000,
                        2020,
                        1960,
                        List.of("AUTOMATIC", "MECHANIC"),
                        List.of("GASOLINE", "DIESEL", "HYBRID", "ELECTRO")
                ).spliterator(),
                false
        ).collect(Collectors.toList());

        assertFalse(cars.isEmpty());
    }

    @Test
    public void whenGetCarsOfUserWithIdThenResultIsNotEmpty() {
        List<Car> cars = StreamSupport.stream(
                service.getCarsOfUserWithId(
                        userService.getWithoutCars(null).iterator().next().getId()
                ).spliterator(),
                false
        ).collect(Collectors.toList());

        assertFalse(cars.isEmpty());
    }
}
