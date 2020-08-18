package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Role;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return userRepository.findByNameLike(searchQuery.toUpperCase());
        } else {
            return userRepository.findAll();
        }
    }

    //todo transactional
    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addNew(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

    //todo transactional
    @Override
    public void deleteById(String id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean userWithNameExists(User user) {
        boolean exists = true;
        if (user != null && user.getUsername() != null) {
            exists = userRepository.existsByUsername(user.getUsername());
        }
        return exists;
    }
}
