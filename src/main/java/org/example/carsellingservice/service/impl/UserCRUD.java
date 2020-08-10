package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.dao.UserDao;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCRUD implements UserService {
    private final UserDetailsRepository userRepository;

    @Autowired
    public UserCRUD(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<UserDao> getWithoutCars(String q) {
        Iterable<UserDao> users;
        if (q == null || q.equals("")) {
            users = userRepository.getAllWithoutCars();
        } else {
            users = userRepository.getWithoutCarsByName(q.toUpperCase());
        }
        return users;
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
