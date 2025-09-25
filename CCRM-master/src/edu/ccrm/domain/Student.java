package edu.ccrm.domain;

import java.util.List;
import java.util.ArrayList;

public class Student extends Person {
    private String regNo;
    private StudentStatus status;
    private List<Course> enrolledCourses;

    public Student(int id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = StudentStatus.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
