package com.model;

/**
 * Created by Junsuk on 2016-08-09.
 */
public class Assignment {
    private String assignmentDeadlline;
    private String assignmentName;
    private String assignmentDescription;

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public void setAssignmentDeadlline(String assignmentDeadlline) {
        this.assignmentDeadlline = assignmentDeadlline;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDeadlline() {
        return assignmentDeadlline;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }
}
