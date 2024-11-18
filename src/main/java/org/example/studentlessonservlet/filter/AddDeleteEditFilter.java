package org.example.studentlessonservlet.filter;

import org.example.studentlessonservlet.entity.User;
import org.example.studentlessonservlet.entity.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addLesson", "/deleteLesson", "/editLesson", "/editStudent", "/deleteStudent", "/addStudent"})
public class AddDeleteEditFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUserType() == UserType.ADMIN) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
