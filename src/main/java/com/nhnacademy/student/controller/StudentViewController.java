package com.nhnacademy.student.controller;

import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if (Objects.isNull(id) || id.isEmpty()) {
            throw new RuntimeException("parameter [id] : null");
        }

        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);

        return "/WEB-INF/student/view.jsp";
    }
}
