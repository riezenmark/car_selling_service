package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//todo SOLID. Разнести интерфейсы, single responsibilty
@Service
public class UserCRUD implements UserService {
    private final UserDetailsRepository userRepository;

    @Autowired
    public UserCRUD(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
