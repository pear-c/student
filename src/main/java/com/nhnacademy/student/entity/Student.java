package com.nhnacademy.student.entity;

import java.time.LocalDateTime;

public class Student {
    private String id;                // 아이디
    private String name;              // 이름
    private Gender gender;            // 성별
    private int age;                  // 나이
    private LocalDateTime createdAt;  // 생성일

    public Student() {

    }

    public Student(String id, String name, Gender gender, int age, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
