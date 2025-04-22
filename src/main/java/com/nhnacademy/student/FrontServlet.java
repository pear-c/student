package com.nhnacademy.student;

import com.nhnacademy.student.controller.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static jakarta.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            //todo 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
            String servletPath = req.getServletPath();
            String method = req.getMethod();

            Command command = resolveCommand(servletPath, method);
            log.info("servletPath={}, method={}, command={}", servletPath, method, command);
            String view = command.execute(req, resp);

            /* day03 - 01.MVC 실습부분
            // 실제 요청 처리할 Servlet 결정.
            String servletPah = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPah);
            rd.include(req, resp);
            // 실제 요청을 처리한 Servlet이 `view`라는 request 속성 값으로 view를 전달해 줌.
            String view = (String) req.getAttribute("view");
             */
            if (view.startsWith(REDIRECT_PREFIX)) {
                // `redirect:`로 시작하면 redirect 처리.
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }
    }

    private Command resolveCommand(String servletPath, String method) {
        Command command = null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();

        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        return command;
    }

    // day03 - 01.MVC 실습부분
    private String resolveServlet(String servletPath) {
        String processingServlet = null;

        if("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        } else if("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        } else if("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }

        return processingServlet;
    }
}
