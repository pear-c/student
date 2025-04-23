package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/student/register.jsp";
    }
}
