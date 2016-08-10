package com.model;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import static javafx.application.Platform.exit;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class UserDBUtil {
    //    DataSource dataSource;
//    DriverManager driverManager;
    Connection connection = null;

    public static void setWarningMsg(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public UserDBUtil() {
        try {

            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin://@192.168.10.230:1521:xe";
            String username = "hr";
            String password = "a1234";
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            setWarningMsg("DB연결을 확인해주세요");
            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet sqlTransaction(String sql, String[] info) {
//        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 1; i <= info.length; i++) {
                statement.setString(i, info[i]);
            }
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
            String sql = "select * from student where student_id=? AND student_pwd=?";
            String[] info = new String[]{id, passwd};
            resultSet = sqlTransaction(sql, info);
            while (resultSet.next()) {
                student.setId(resultSet.getString(1));
                student.setName(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    public Lecturer getLecturer(String id, String passwd) {
        Lecturer lecturer = null;
        ResultSet resultSet;

        String sql = "select * from lecturer where lecturer_id=? AND lecturer_pwd=?";
        String[] info = new String[]{id, passwd};
        resultSet = sqlTransaction(sql, info);
        try {
            while (resultSet.next()) {
                lecturer.setId(resultSet.getString(1));
                lecturer.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return lecturer;
    }

    public ArrayList<Lecture> getLectures(String id) {
        ArrayList<Lecture> lectures = new ArrayList<>();
        Lecture lecture = null;
        ResultSet resultSet = null;
        String sql = "select * from LECTURE where LECTURE_ID=?";
        String[] info = new String[]{id};
        resultSet = sqlTransaction(sql, info);
        try {
            while (resultSet.next()) {
                String lectureId = resultSet.getString(1);
                String lectureClass = resultSet.getString(2);
                String lectureName = resultSet.getString(3);
                lecture = new Lecture(lectureId, lectureClass, lectureName);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectures;
    }

    public ArrayList<Assignment> getAssignment(String lectureId) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        ResultSet resultSet = null;
        String sql = "select * from assignment where lecture_id=?";
        String[] info = new String[]{lectureId};
        resultSet = sqlTransaction(sql, info);
        try {
            while (resultSet.next()) {
                Assignment assignment = null;
                assignment.setAssignmentName(resultSet.getString(1));
                assignment.setAssignmentDeadlline(resultSet.getString(2));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    //DB에 학생정보를 등록함.
    public void addNewStudent(Student student) {
        String sql = "insert into student value(?, ?, ?, ?)";
        String[] info = new String[]{student.getId(),student.getName(),student.getLecture(),student.getPasswd()};
        sqlTransaction(sql,info);
    }


    //Adding

}
