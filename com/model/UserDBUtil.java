package com.model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class UserDBUtil {
//    DataSource dataSource;
//    DriverManager driverManager;
    Connection connection = null;
    public UserDBUtil(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin://@192.168.10.230:1521:xe";
            String username="hr";
            String password="a1234";
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet sqlTransaction(String sql, String id){
//        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
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
        String sql = "select * from LECTURE where LECTURE_ID=?";
        resultSet = sqlTransaction(sql, id);
        try {
            while (resultSet.next()) {
                String lectureId = resultSet.getString(1);
                String lectureClass = resultSet.getString(2);
                String lectureName = resultSet.getString(3);
                lecture = new Lecture(lectureId,lectureClass,lectureName);
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
