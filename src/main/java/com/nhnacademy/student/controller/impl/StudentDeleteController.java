package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.controller.Command;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        studentRepository.deleteById(id);
        return "redirect:/student/list.do";
    }
}
