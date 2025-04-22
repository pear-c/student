package com.nhnacademy.student;

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
            // 실제 요청 처리할 Servlet 결정.
            String servletPah = resolveServlet(req.getServletPath());

            // 실제 요청을 처리할 Servlet으로 요청을 전달하여 처리 결과를 include시킴.
            RequestDispatcher rd = req.getRequestDispatcher(servletPah);
            rd.include(req, resp);

            // 실제 요청을 처리한 Servlet이 `view`라는 request 속성 값으로 view를 전달해 줌.
            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                // `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            // 에러가 발생한 경우는 error page로 지정된 `/error.jsp`에게 view 처리를 위임.
            req.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.setAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE, ex.getClass());
            req.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getMessage());
            req.setAttribute(RequestDispatcher.ERROR_EXCEPTION, ex);
            req.setAttribute(RequestDispatcher.ERROR_REQUEST_URI, req.getRequestURI());

            RequestDispatcher rd = req.getRequestDispatcher("/error.do");
            rd.forward(req, resp);
        }
    }

    // 요청 URL에 따라 실제 요청을 처리할 Servlet 결정.
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
