package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //todo transactional
    //todo page
    //todo criteria
    @Override
    public List<User> getUsers(String searchQuery) {
        if (searchQuery != null && !searchQuery.equals("")) {
            return userRepository.findByNameOrEmail(searchQuery.toUpperCase());
        } else {
            return userRepository.findAll();
        }
    }

    //todo transactional
    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    //todo transactional
    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
