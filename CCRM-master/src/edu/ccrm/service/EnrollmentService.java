package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Grade;
import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Student student, Course course);
    void unenrollStudent(Student student, Course course);
    void recordGrade(Student student, Course course, Grade grade);
    double calculateGPA(Student student);
    List<Enrollment> getEnrollmentsByStudent(Student student);
}
