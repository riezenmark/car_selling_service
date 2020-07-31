package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;

public interface UserService {
    Iterable<User> getAll();

    User getById(String id);

    void deleteById(String id);
}
