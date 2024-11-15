package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.service.LessonService;
import org.example.studentlessonservlet.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/editLesson")
public class EditLessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lesson lesson = lessonService.findLessonById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/editLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("lessonId"));
            String name = req.getParameter("name");
            Date date = DateUtil.webTimeStringToDate(req.getParameter("duration"));
            String lecturerName = req.getParameter("lName");
            double price = Double.parseDouble(req.getParameter("price"));
            Lesson lesson = Lesson.builder()
                    .id(id)
                    .name(name)
                    .duration(date)
                    .lecturerName(lecturerName)
                    .price(price)
                    .build();
                lessonService.updateLesson(lesson);
            resp.sendRedirect("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
