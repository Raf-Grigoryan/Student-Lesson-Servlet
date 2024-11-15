package org.example.studentlessonservlet.servlet;

import org.example.studentlessonservlet.entity.Student;
import org.example.studentlessonservlet.service.LessonService;
import org.example.studentlessonservlet.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.getStudentById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id  = Integer.parseInt(req.getParameter("id"));
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    int age = Integer.parseInt(req.getParameter("age"));
    Student student = Student.builder()
            .id(id)
            .name(name)
            .surname(surname)
            .email(email)
            .age(age)
            .build();
    studentService.updateStudent(student);
    resp.sendRedirect("/index.jsp");
    }
}