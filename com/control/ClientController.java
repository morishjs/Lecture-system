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

    public static final int RESULT_OK = 1;
    public static final int LECTURE_NO_EXIST = 2;
    public static final int STUDENT_REG_ERROR = 3;

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
    public int signup(String id, String name, String pw, String lectureCode) {
        //Insert data into the database.
        int result;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setPasswd(pw);
        ArrayList<Lecture> lecture = userDBUtil.getLectures(lectureCode);
        if (lecture == null) {
            result = LECTURE_NO_EXIST;
        }
        else {
            student.setLecture(lecture.get(0));
            result = userDBUtil.addNewStudent(student);
        }
        return result;

        //TODO: Signup
        //Step1. Inflate the view of signup.
        //Step2. When the form is completed, button onclicked, get the data from textfield.
        //Step3. Add the information to Database. UserDBUtil class take a role to manage 'load and 'store' transaction.
        //userDBUtil.add(id, passwd);
    }


    public Lecturer l_Login(String id, String passwd){
        Lecturer lecturer = userDBUtil.getLecturer(id,passwd);
        ArrayList<Lecture> lectures = null;
        ArrayList<Assignment> assignments = null;

        if(lecturer == null)
            return null;
        else{
            //lecturer가 담당하고 있는 lecture들을 lectures 에 add에 해줘야 함.
            lectures = userDBUtil.getLectures(lecturer);
//            lectures 에 assignment를 할당해줘야함.
            if(lectures == null)
                return null;
            else{
                lecturer.setLectures(lectures);
                int i = 0;
                for (Lecture lecture : lectures) {
                    assignments = userDBUtil.getAssignment(lecture.getLectureId());
                    if (assignments != null) {
                        lecture.setAssignmentList(assignments);
                        lectures.set(i, lecture);
                        i++;
                    }
                }
                lecturer.setLectures(lectures);
            }
            return lecturer;
        }
    }

    public void showSignUpView(){
        new SignUp();
    }//end showSignUpView()

    public void showCalender(ArrayList<Lecture> lecture) {
            swingCalender = new SwingCalender(lecture);
    }
    public SwingCalender getCalender(){
    	return swingCalender;
    }
}
