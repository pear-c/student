package com.nhnacademy.student.controller.impl;

import com.nhnacademy.day04.annotation.RequestMapping;
import com.nhnacademy.student.controller.Command;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.GET)
public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)) {
            throw new RuntimeException("Student not found : " + id);
        }
        req.setAttribute("student", student);
        req.setAttribute("action", "/student/update.do");

        return "/WEB-INF/student/register.jsp";
    }
}
