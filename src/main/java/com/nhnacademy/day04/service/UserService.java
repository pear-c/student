package com.nhnacademy.day04.service;

import com.nhnacademy.day04.annotation.Autowired;
import com.nhnacademy.day04.entity.User;
import com.nhnacademy.day04.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String userName) {
        return userRepository.findByName(userName);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
