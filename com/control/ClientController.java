package com.control;

import com.model.Assignment;
import com.model.Lecture;
import com.model.Student;
import com.model.UserDBUtil;
import oracle.jdbc.util.Login;
import com.view.*;
import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class ClientController {
    UserDBUtil userDBUtil;
    //For student login.

    Assign assign = null;
    LogIn login = null;
    SignUp signUp = null;
    SwingCalender swingCalender = null;


    public void init(){
        new LogIn();
    }

            public Student login(String id, String passwd) {
                //TODO: Login
                //Step1 : Verify specified information from database. (Select * from student where student_id = id )
                //Step2 : If there is no problem, then create Lecture object and inject dependency into Student object.
                //(Select * from lecture where lecture_id = student.lecture_id)
                //Step3 : If there is no problem, then create assignment object.
                //Step4 : lecture.add(assignments) -> student.add(lecture)

                Student student = userDBUtil.getStudent(id, passwd);
                Lecture lecture;
                ArrayList<Assignment> assignments;
                if (student == null) {
                    //TODO: Print alert message.
            return null;
        } else {
            lecture = userDBUtil.getLecture(student.getId());
            if (lecture == null) {
                //No course to take.
                return null;
            } else {
                assignments = userDBUtil.getAssignment(lecture.getLectureId());
                lecture.setAssignmentList(assignments);
            }
            student.setLecture(lecture);
        }
        return student;
    }

    void signup() {
        //Insert data into the database.


        //TODO: Signup
        //Step1. Inflate the view of signup.
        //Step2. When the form is completed, button onclicked, get the data from textfield.
        //Step3. Add the information to Database. UserDBUtil class take a role to manage 'load and 'store' transaction.
        //userDBUtil.add(id, passwd);
    }


}
