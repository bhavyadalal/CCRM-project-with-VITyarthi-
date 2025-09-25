package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {
    private List<Course> courses;

    public CourseServiceImpl() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public Course getCourseByCode(String code) {
        Optional<Course> course = courses.stream()
                                        .filter(c -> c.getCode().equals(code))
                                        .findFirst();
        return course.orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public void updateCourse(Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equals(updatedCourse.getCode())) {
                courses.set(i, updatedCourse);
                return;
            }
        }
    }

    @Override
    public void assignInstructor(String courseCode, Instructor instructor) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            course.setInstructor(instructor);
        }
    }

    @Override
    public List<Course> searchCourses(String query, String department, Semester semester) {
        return courses.stream()
                .filter(course -> (query == null || course.getTitle().toLowerCase().contains(query.toLowerCase()) || course.getCode().toLowerCase().contains(query.toLowerCase())))
                .filter(course -> (department == null || course.getDepartment().equalsIgnoreCase(department)))
                .filter(course -> (semester == null || course.getSemester().equals(semester)))
                .collect(Collectors.toList());
    }
}
