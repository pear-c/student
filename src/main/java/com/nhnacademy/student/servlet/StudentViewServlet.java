package com.nhnacademy.student.servlet;

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
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        //todo id null check
        if (Objects.isNull(id) || id.isEmpty()) {
            throw new RuntimeException("parameter [id] : null");
        }

        //todo student 조회
        Student student = studentRepository.getStudentById(id);
        log.error("student:{}", student);
        req.setAttribute("student", student);

//        //todo /student/view.jsp <-- forward
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/student/view.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/WEB-INF/student/view.jsp");
    }
}
