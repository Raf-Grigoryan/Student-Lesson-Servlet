package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.entity.Student;
import org.example.studentlessonservlet.entity.User;
import org.example.studentlessonservlet.service.LessonService;
import org.example.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        User user = (User) req.getSession().getAttribute("user");
        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .lesson(lessonService.findLessonById(lessonId))
                .user(user)
                .build();
        studentService.addStudent(student);
        resp.sendRedirect("/index.jsp");
    }
}
