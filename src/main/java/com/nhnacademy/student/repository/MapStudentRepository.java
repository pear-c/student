package com.nhnacademy.student.repository;

import com.nhnacademy.student.entity.Student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapStudentRepository implements StudentRepository {
    Map<String, Student> map = new HashMap<>();

    @Override
    public void save(Student student) {
        map.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        map.put(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        map.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return map.get(id);
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new LinkedList<>();
        for(String id : map.keySet()) {
            students.add(map.get(id));
        }
        return students;
    }

    @Override
    public boolean existById(String id) {
        return map.containsKey(id);
    }
}
