package com.model;

/**
 * Created by Junsuk on 2016-08-09.
 */

public class Student extends Person{

    private Lecture lecture;
    private String type;

    public void setStudentID(String studentID) {
        id = studentID;
    }

    public void setStudentName(String studentName) {
        name = studentName;
    }

    public void setLecture(Lecture lecture){
        this.lecture = lecture;
    }

    public String getLecture() {
        return lecture.getLectureName();
    }

    public String getPasswd() {
        return this.getPasswd();
    }

    @Override
    public String getType(){
        return type;
    }


}
