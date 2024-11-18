package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.entity.User;
import org.example.studentlessonservlet.service.LessonService;
import org.example.studentlessonservlet.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addLesson")
public class AddLessonServlet extends HttpServlet {

    private final LessonService lessonService = new LessonService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String name = req.getParameter("name");
            Date date = DateUtil.webTimeStringToDate(req.getParameter("duration"));
            String lectureName = req.getParameter("lName");
            double price = Double.parseDouble(req.getParameter("price"));
            Lesson lesson = Lesson.builder()
                    .name(name)
                    .duration(date)
                    .lecturerName(lectureName)
                    .price(price)
                    .user(user)
                    .build();
            lessonService.addLesson(lesson);
            resp.sendRedirect("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
