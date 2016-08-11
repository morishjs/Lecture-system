package com.control;

import com.model.*;
import oracle.jdbc.util.Login;
import com.view.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class ClientController {
    UserDBUtil userDBUtil;
    private static ClientController instance = new ClientController();
    private Session session=new Session();
    Assign assign = null;
    LogIn login = null;
    SignUp signUp = null;
    SwingCalender swingCalender = null;
//
//    Session session = null;
//
//    session.setType("Stduent");
//    session.setId(student.getId());

    private ClientController(){

    }
    public static ClientController getInstance(){
        return instance;
    }
    public void init() {
        userDBUtil = new UserDBUtil();
        new LogIn();
    }

    public void setSession(Person person){
        session.setId(person.getId());
        session.setType(person.getType());
    }

    public Session getSession(){
        return session;
    }

    public Student login(String id, String passwd) {
        //TODO: Login
        //Step1 : Verify specified information from database. (Select * from student where student_id = id )
        //Step2 : If there is no problem, then create Lecture object and inject dependency into Student object.
        //(Select * from lecture where lecture_id = student.lecture_id)
        //Step3 : If there is no problem, then create assignment object.
        //Step4 : lecture.adid(assignments) -> student.add(lecture)

        Student student = userDBUtil.getStudent(id, passwd);
        ArrayList<Lecture> lectures = null;
        ArrayList<Assignment> assignments;

        if (student == null) {
            //TODO: Print alert message.
            return null;
        } else {
            lectures = userDBUtil.getLectures(student.getLecture());
            if (lectures == null) {
                //No course to take.
                return null;
            } else {
                assignments = userDBUtil.getAssignment(lectures.get(0).getLectureId());
                lectures.get(0).setAssignmentList(assignments);
            }
            student.setLecture(lectures.get(0));
        }
        return student;
    }

    //회원가입.
    public void signup(String id, String name, String pw, String lectureCode) {
        //Insert data into the database.

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setPasswd(pw);
        ArrayList<Lecture> lecture = userDBUtil.getLectures(lectureCode);
        student.setLecture(lecture.get(0));
        userDBUtil.addNewStudent(student);

        //TODO: Signup
        //Step1. Inflate the view of signup.
        //Step2. When the form is completed, button onclicked, get the data from textfield.
        //Step3. Add the information to Database. UserDBUtil class take a role to manage 'load and 'store' transaction.
        //userDBUtil.add(id, passwd);
    }


    public Lecturer l_Login(String id, String passwd){
        Lecturer lecturer = userDBUtil.getLecturer(id,passwd);
        ArrayList<Lecture> lectures = null;
        ArrayList<Assignment> assignments = new ArrayList<>();

        if(lecturer == null)
            return null;
        else{
            lectures = userDBUtil.getLectures(id);
            if(lectures == null)
                return null;
            else{
                for (Lecture lecture : lectures) {
                    assignments = userDBUtil.getAssignment(lecture.getLectureId());
                    lecture.setAssignmentList(assignments);
                    lectures.add(lecture);
                }
                lecturer.setLectures(lectures);
            }
            return lecturer;
        }
    }

    public void showSignUpView(){
        new SignUp();
    }//end showSignUpView()

    public void showCalender() {
        new SwingCalender();
    }
}
