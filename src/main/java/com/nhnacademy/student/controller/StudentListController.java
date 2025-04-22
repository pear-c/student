package com.nhnacademy.student.controller;

import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class StudentListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);
        return "/WEB-INF/student/list.jsp";
    }
}
