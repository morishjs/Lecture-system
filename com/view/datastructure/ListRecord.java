package com.view.datastructure;

/**
 * Created by Junsuk on 2016-08-14.
 */
public class ListRecord {
    private String studentName;
    private String assignName;
    private String studentId;

    public String getStudentName() {
        return studentName;
    }



    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String string) {
        this.assignName = string;
    }

    public void setName(String string) {
        studentName = string;
    }

    public void setId(String string) {
        studentId = string;
    }

    public String getStudentId() {
        return studentId;
    }
}
