package com.model;


import java.util.ArrayList;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class Lecture {

    private String lectureId;
    private String lectureClassroom;
    private String lectureName;
    private ArrayList<Assignment> assignmentList;

    Lecture(){
        assignmentList = new ArrayList<>();
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public void setLectureClassroom(String lectureClassroom) {
        this.lectureClassroom = lectureClassroom;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public void addLecture(Assignment e){
        assignmentList.add(e);
    }
    public String getLectureId() {
        return lectureId;
    }

    public void setAssignmentList(ArrayList<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public String getLectureClassroom() {
        return lectureClassroom;
    }

    public String getLectureName() {
        return lectureName;
    }

    public ArrayList<Assignment> getAssignmentList() {
        return assignmentList;
    }
}
