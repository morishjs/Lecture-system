package com.model;

/**
 * Created by Junsuk on 2016-08-09.
 */

public class Student extends Person{

    private Lecture lecture;

    public void setLecture(Lecture lecture){
        this.lecture = lecture;
    }

    public String getLecture() {
        return lecture.getLectureId();
    }

    public Lecture getLectureObj() {
        return lecture;
    }


    public String getPasswd() {
        return this.passwd;
    }
    @Override
    public String getType(){
        return type;
    }
}
