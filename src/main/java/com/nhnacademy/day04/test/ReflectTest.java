package com.nhnacademy.day04.test;

import com.nhnacademy.day04.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// 실습01 - Reflection API 이용한 필드 접근
public class ReflectTest {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName(User.class.getName());
            Object user =clazz.getDeclaredConstructor().newInstance();
            Field userNameField =clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");
            String userName = (String) userNameField.get(user);
            Field userAgeField =clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);
            int userAge = userAgeField.getInt(user);
            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
