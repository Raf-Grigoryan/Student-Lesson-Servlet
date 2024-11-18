package org.example.studentlessonservlet.service;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.entity.Lesson;
import org.example.studentlessonservlet.entity.User;
import org.example.studentlessonservlet.entity.UserType;
import org.example.studentlessonservlet.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addLesson(Lesson lesson) {
        try {
            String sql = "INSERT INTO lesson(name,duration,lecturer_name,price,user_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, DateUtil.dateToSqlTimeString(lesson.getDuration()));
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLesson(int id) {
        String sql = "DELETE FROM lesson WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLesson(Lesson lesson) {
        try {
            String sql = "UPDATE lesson SET name = ?, duration = ?, lecturer_name = ?, price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, DateUtil.dateToSqlTimeString(lesson.getDuration()));
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT l.id,l.name,l.lecturer_name,l.price,l.duration,u.id AS user_id,u.name AS user_name,u.surname,u.email,u.password,u.user_type FROM lesson AS l JOIN user AS u ON l.user_id = u.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                        .user(User.builder()
                                .id(resultSet.getInt("user_id"))
                                .name(resultSet.getString("user_name"))
                                .surname(resultSet.getString("surname"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                .userType(UserType.valueOf(resultSet.getString("user_type")))
                                .build())
                        .build();
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson findLessonById(int id) {
        Lesson lesson = null;
        try {
            String sql = "SELECT * FROM lesson WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesson;
    }

}
