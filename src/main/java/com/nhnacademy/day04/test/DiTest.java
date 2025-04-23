package com.nhnacademy.day04.test;

import com.nhnacademy.day04.entity.User;
import com.nhnacademy.day04.service.UserService;
import com.nhnacademy.day04.util.InjectUtil;

public class DiTest {
    public static void main(String[] args) {
        UserService userService = InjectUtil.getObject(UserService.class);
        User user = new User("marco1", 10);
        userService.addUser(user);
        System.out.println(userService.getUser("marco1"));
    }
}
