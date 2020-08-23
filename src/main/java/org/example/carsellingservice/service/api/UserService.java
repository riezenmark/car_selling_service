package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers(String searchQuery);

    void deleteById(Long id);

    User getById(Long id);

    boolean userWithNameExists(User user);

    void addNew(User user);

    User update(Long id, User user);
}
