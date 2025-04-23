package com.nhnacademy.day04.repository;

import com.nhnacademy.day04.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public User findByName(String userName) {
        return users.stream()
                .filter(o -> o.getUserName().equals(userName)).findFirst().orElse(null);
    }

    public void save(User user) {
        this.users.add(user);
    }
}
