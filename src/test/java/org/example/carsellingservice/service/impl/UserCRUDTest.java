package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.dao.UserDao;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserCRUDTest {

    @Autowired
    private UserService service;

    @Test
    public void whenSearchForUsersByQueryIgnoringCaseThenResultIsNotEmpty() {
        String query = "rIeZenMaRK";

        List<UserDao> users = StreamSupport.stream(
                service.getWithoutCars(query).spliterator(), false
        ).collect(Collectors.toList());

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenSearchForUsersByEmptyQueryThenResultIsNotEmpty() {
        String query = "";

        List<UserDao> users = StreamSupport.stream(
                service.getWithoutCars(query).spliterator(), false
        ).collect(Collectors.toList());

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenSearchForUsersByNullQueryThenResultIsNotEmpty() {
        List<UserDao> users = StreamSupport.stream(
                service.getWithoutCars(null).spliterator(), false
        ).collect(Collectors.toList());

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenDeleteAllUsersByIdThenThereIsNoMoreUsers() {
        List<UserDao> users = StreamSupport.stream(
                service.getWithoutCars(null).spliterator(), false
        ).collect(Collectors.toList());

        for (UserDao user : users) {
            service.deleteById(user.getId());
        }

        users = StreamSupport.stream(
                service.getWithoutCars(null).spliterator(), false
        ).collect(Collectors.toList());

        assertTrue(users.isEmpty());
    }
}