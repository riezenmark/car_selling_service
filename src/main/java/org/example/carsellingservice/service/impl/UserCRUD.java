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
        if (q == null || q.equals("")) {
            return userRepository.getAllWithoutCars();
        } else {
            return userRepository.getWithoutCarsByName(q.toUpperCase());
        }
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
