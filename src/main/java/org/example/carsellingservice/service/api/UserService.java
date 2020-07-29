package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;

public interface UserService {
    Iterable<User> getAll();

    User getById(Long id);

    User add(User user);

    User updateById(Long id, User user);

    void deleteById(Long id);
}
