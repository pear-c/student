package com.nhnacademy.student.servlet;

import com.nhnacademy.student.entity.Gender;
import com.nhnacademy.student.entity.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //todo  /student/register.jsp forward 합니다.
//        req.setAttribute("action", "/student/register");
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/student/register.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/WEB-INF/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        //todo null check
        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
            throw new RuntimeException("id, name, gender, age 확인해주세요!");
        }

        //todo save 구현
        Student student = new Student(id, name, gender, age, LocalDateTime.now());
        studentRepository.save(student);

//        //todo redirect /student/view?id=student1
//        resp.sendRedirect("/student/view?id=" + id);
        req.setAttribute("view", "redirect:/student/view.do?id=" + id);
    }
}
