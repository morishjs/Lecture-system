package com.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class UserDBUtil {
    DataSource dataSource;

    public ResultSet sqlTransaction(String sql, String id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public Student getStudent(String id, String passwd) {

        Student student = null;
        ResultSet resultSet;
        try {
            String sql = "select * from student where student_id=?";
            resultSet = sqlTransaction(sql,id);
            while (resultSet.next()) {
                student.setStudentID(resultSet.getString(1));
                student.setStudentName(resultSet.getString(2));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Lecture getLecture(String id) {
        Lecture lecture = null;
        ResultSet resultSet = null;
        String sql = "select * from lecture where lecture_id=?";
        resultSet = sqlTransaction(sql, id);
        try {
            while (resultSet.next()) {
                lecture.setLectureId(resultSet.getString(1));
                lecture.setLectureClassroom(resultSet.getString(2));
                lecture.setLectureName(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecture;
    }

    public ArrayList<Assignment> getAssignment(String lectureId) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select * from assignment where lecture_id=?";
        resultSet = sqlTransaction(sql, lectureId);
        try {
            while (resultSet.next()) {
                Assignment assignment=null;
                assignment.setAssignmentName(resultSet.getString(1));
                assignment.setAssignmentDeadlline(resultSet.getString(2));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    //Adding

}
