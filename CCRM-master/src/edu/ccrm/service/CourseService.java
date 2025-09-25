package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import java.util.List;

public interface CourseService {
    void addCourse(Course course);
    Course getCourseByCode(String code);
    List<Course> getAllCourses();
    void updateCourse(Course course);
    void assignInstructor(String courseCode, Instructor instructor);
    List<Course> searchCourses(String query, String department, Semester semester);
}
