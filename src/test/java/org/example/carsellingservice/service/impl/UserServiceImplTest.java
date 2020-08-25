package org.example.carsellingservice.service.impl;

import lombok.var;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/fill-base-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-base-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void whenSearchForUsersByQueryIgnoringCaseThenResultIsNotEmpty() {
        String query = "aDmIn";

        List<User> users = service.getUsers(query);

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenSearchForUsersByEmptyQueryThenResultIsNotEmpty() {
        String query = "";

        List<User> users = service.getUsers(query);

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenSearchForUsersByNullQueryThenResultIsNotEmpty() {
        List<User> users = service.getUsers(null);

        assertFalse(users.isEmpty());
    }

    @Test
    public void whenDeleteAllUsersByIdThenThereIsNoMoreUsers() {
        List<User> users = service.getUsers(null);

        for (var user : users) {
            service.deleteById(user.getId());
        }

        users = service.getUsers(null);

        assertTrue(users.isEmpty());
    }
}