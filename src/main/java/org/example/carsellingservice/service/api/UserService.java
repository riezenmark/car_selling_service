package org.example.carsellingservice.service.api;

import org.example.carsellingservice.dao.UserDao;

public interface UserService {
    Iterable<UserDao> getWithoutCars(String q);

    void deleteById(String id);
}
