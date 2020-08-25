package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers(final String searchQuery);

    void deleteById(final Long id);

    User getById(final Long id);

    boolean userWithNameExists(final User user);

    void addNew(final User user);

    User update(final Long id, final User user);
}
