package com.model;

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


}
