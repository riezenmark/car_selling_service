package org.example.carsellingservice.service.api;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.domain.User;

public interface UserService {
    Iterable<UserDao> getAllWithoutCars();

    User getById(String id);

    void deleteById(String id);
}
