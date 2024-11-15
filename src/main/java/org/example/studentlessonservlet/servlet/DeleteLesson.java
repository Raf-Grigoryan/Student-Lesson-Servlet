package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteLesson")
public class DeleteLesson extends HttpServlet {

    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        lessonService.deleteLesson(lessonId);
        req.getRequestDispatcher("/lesson").forward(req, resp);
    }
}
