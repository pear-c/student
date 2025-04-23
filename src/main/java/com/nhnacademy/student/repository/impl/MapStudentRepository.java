package com.nhnacademy.student.repository.impl;

import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository {

    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return studentsMap.get(id);
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new LinkedList<>();
        for(String id : studentsMap.keySet()) {
            students.add(studentsMap.get(id));
        }
        return students;
    }

    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
}
