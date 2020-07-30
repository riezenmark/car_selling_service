package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCRUD implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserCRUD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User add(User user) {
        User result;
        if (user.getId() == null || userRepository.findById(user.getId()).isEmpty()) {
            user.setCreationDate(LocalDateTime.now());
            result = userRepository.save(user);
        } else {
            result = null;
        }
        return result;
    }

    //todo Проверить на затирание not null полей и сохранение без repo.save
    @Override
    public User updateById(Long id, User user) {
        User userFromDatabase = this.getById(id);
        BeanUtils.copyProperties(user, userFromDatabase, "id");
        return userRepository.save(userFromDatabase);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
