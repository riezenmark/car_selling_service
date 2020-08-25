package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Role;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(final String searchQuery) {
        return Optional.ofNullable(searchQuery)
                .map(s -> userRepository.findByUsernameLike(s.toUpperCase()))
                .orElseGet(userRepository::findAll);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addNew(final User user) {
        user.setActive(true);
        user.setAuthorities(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(final Long id, final User user) {
        User userFromRepository = userRepository.findById(id).orElse(null);
        if (userFromRepository != null && user != null && userFieldsAreValid(user)) {
            userFromRepository.setAuthorities(user.getAuthorities());
            userFromRepository.setUsername(user.getUsername());
            userFromRepository = userRepository.save(userFromRepository);
        }
        return userFromRepository;
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean userWithNameExists(final User user) {
        boolean exists = true;
        if (user != null && user.getUsername() != null) {
            exists = userRepository.existsByUsername(user.getUsername());
        }
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    private boolean userFieldsAreValid(User user) {
        return user.getAuthorities() != null
                && !user.getAuthorities().isEmpty()
                && !userWithNameExists(user);
    }
}
