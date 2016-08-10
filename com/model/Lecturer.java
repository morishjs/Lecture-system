package com.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class Lecturer extends Person {
    private HashMap<String,Lecture> lectureList;
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

    @Override
    public String getType(){
        return type;
    }


}
