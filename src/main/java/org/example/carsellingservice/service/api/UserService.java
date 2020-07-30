package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Iterable<User> getAll();

    User getById(Long id);

    User add(User user);

    User updateById(Long id, User user);

    void deleteById(Long id);

    boolean register(User user);
}
