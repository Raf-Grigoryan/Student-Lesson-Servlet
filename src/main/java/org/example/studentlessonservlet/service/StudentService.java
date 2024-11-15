package org.example.studentlessonservlet.service;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.entity.Student;
import org.example.studentlessonservlet.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addStudent(Student student) {
        try {
            String sql = "INSERT INTO student(name,surname,email,age,lesson_id) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.name,s.surname,s.email,s.age, l.id AS lesson_id,l.name AS lesson_name,l.duration,l.lecturer_name,l.price FROM student AS s JOIN lesson AS l ON s.lesson_id = l.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(Lesson.builder()
                                .id(resultSet.getInt("lesson_id"))
                                .name(resultSet.getString("lesson_name"))
                                .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                                .lecturerName(resultSet.getString("lecturer_name"))
                                .price(resultSet.getDouble("price"))
                                .build())
                        .build();
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        Student student = null;
        try {
            String sql = "SELECT s.id, s.name,s.surname,s.email,s.age, l.id AS lesson_id,l.name AS lesson_name,l.duration,l.lecturer_name,l.price FROM student AS s JOIN lesson AS l ON s.lesson_id = l.id WHERE s.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(Lesson.builder()
                                .id(resultSet.getInt("lesson_id"))
                                .name(resultSet.getString("lesson_name"))
                                .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                                .lecturerName(resultSet.getString("lecturer_name"))
                                .price(resultSet.getDouble("price"))
                                .build())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public void removeStudentById(int id) {
        try {
            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            String sql = "UPDATE student SET name = ? , surname = ? , email = ? , age = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
