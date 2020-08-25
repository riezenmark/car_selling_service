package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;

import java.util.List;

public interface UserService {
    List<User> getWithoutCars(String q);

    void deleteById(String id);
}
