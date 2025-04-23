package com.nhnacademy.student.controller.impl;

import com.nhnacademy.day04.annotation.RequestMapping;
import com.nhnacademy.student.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.servlet.RequestDispatcher.*;

@RequestMapping(value = "/error.do", method = RequestMapping.Method.GET)
public class ErrorController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        return "/WEB-INF/error.jsp";
    }
}
