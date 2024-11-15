package org.example.studentlessonservlet.service;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.entity.Lesson;
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
            String sql = "INSERT INTO lesson(name,duration,lecturer_name,price) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, DateUtil.dateToSqlTimeString(lesson.getDuration()));
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
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
            String sql = "SELECT * FROM lesson";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(DateUtil.sqlStringTimeToDate(resultSet.getString("duration")))
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
