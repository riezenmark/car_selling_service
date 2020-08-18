package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;

import java.util.List;

public interface UserService {
    //todo page
    //todo dto?
    List<User> getUsers(String searchQuery);

    void deleteById(String id);

    //todo dto?
    User getById(String id);

    boolean userWithNameExists(User user);

    void addNew(User user);
}
