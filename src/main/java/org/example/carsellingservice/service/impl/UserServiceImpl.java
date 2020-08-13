package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userRepository;

    @Override
    public List<User> getWithoutCars(String q) {
        List<User> users;
        //todo использовать опшионалы
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
