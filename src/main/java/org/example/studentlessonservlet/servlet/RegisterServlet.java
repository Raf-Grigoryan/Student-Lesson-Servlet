package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.User;
import org.example.studentlessonservlet.entity.UserType;
import org.example.studentlessonservlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            UserType userType = UserType.valueOf(req.getParameter("user_type"));
            user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(userType)
                    .build();
            userService.addUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/register");
        }

    }
}
