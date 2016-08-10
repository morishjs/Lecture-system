package com.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class Lecturer extends Person {
    HashMap<String,Lecture> lectureList;

    Lecturer(){
        lectureList = new HashMap<>();
    }

    public void addLecture(Lecture lecture) {
        lectureList.put(lecture.getLectureId(), lecture);
    }


    public void setLectures(ArrayList<Lecture> lectures) {
        for (Lecture lecture : lectures) {
            lectureList.put(lecture.getLectureName(), lecture);
        }
    }

    public void setLecturerID(String string) {
        this.id = string;
    }

    public void setLecturerName(String string) {
        this.name = string;
    }
}
