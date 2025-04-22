package com.nhnacademy.student.controller;

import com.nhnacademy.student.entity.Gender;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudentUpdateController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if(Objects.nonNull(req.getParameter("gender"))) {
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))) {
            age = Integer.parseInt(req.getParameter("age"));
        }

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        Student updateStudent = new Student(id, name, gender, age, LocalDateTime.now());
        studentRepository.update(updateStudent);

        return "redirect:/student/view.do?id=" + id;
    }
}
