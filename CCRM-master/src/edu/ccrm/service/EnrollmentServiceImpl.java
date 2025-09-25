package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Grade;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentServiceImpl implements EnrollmentService {
    private List<Enrollment> enrollments;

    public EnrollmentServiceImpl() {
        this.enrollments = new ArrayList<>();
    }

    @Override
    public void enrollStudent(Student student, Course course) {
        // Check for business rules, e.g., max credits per semester
        // For now, just add the enrollment with a default grade (e.g., F)
        enrollments.add(new Enrollment(student, course, Grade.F));
    }

    @Override
    public void unenrollStudent(Student student, Course course) {
        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
    }

    @Override
    public void recordGrade(Student student, Course course, Grade grade) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student) && enrollment.getCourse().equals(course)) {
                enrollment.setGrade(grade);
                return;
            }
        }
    }

    @Override
    public double calculateGPA(Student student) {
        double totalGradePoints = 0;
        int totalCredits = 0;

        List<Enrollment> studentEnrollments = getEnrollmentsByStudent(student);

        for (Enrollment enrollment : studentEnrollments) {
            totalGradePoints += enrollment.getGrade().getGradePoint() * enrollment.getCourse().getCredits();
            totalCredits += enrollment.getCourse().getCredits();
        }

        return totalCredits == 0 ? 0 : totalGradePoints / totalCredits;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }
}
