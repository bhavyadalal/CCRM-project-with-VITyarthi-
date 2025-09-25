package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.List;

public class TranscriptServiceImpl implements TranscriptService {
    private EnrollmentService enrollmentService;

    public TranscriptServiceImpl(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String generateTranscript(Student student) {
        StringBuilder transcript = new StringBuilder();
        transcript.append("\n--- Transcript for ").append(student.getFullName()).append(" (ID: ").append(student.getId()).append(") ---\n");
        transcript.append("Registration No: ").append(student.getRegNo()).append("\n");
        transcript.append("Status: ").append(student.getStatus()).append("\n\n");

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(student);

        if (enrollments.isEmpty()) {
            transcript.append("No courses enrolled.\n");
        } else {
            transcript.append(String.format("%-10s %-30s %-10s %-10s\n", "Course Code", "Course Title", "Credits", "Grade"));
            transcript.append(String.format("%-10s %-30s %-10s %-10s\n", "-----------", "-------------", "-------", "-----"));
            for (Enrollment enrollment : enrollments) {
                transcript.append(String.format("%-10s %-30s %-10d %-10s\n",
                        enrollment.getCourse().getCode(),
                        enrollment.getCourse().getTitle(),
                        enrollment.getCourse().getCredits(),
                        enrollment.getGrade().name()));
            }
            transcript.append("\n");
            transcript.append("GPA: ").append(String.format("%.2f", enrollmentService.calculateGPA(student))).append("\n");
        }
        transcript.append("----------------------------------------------------\n");
        return transcript.toString();
    }
}
