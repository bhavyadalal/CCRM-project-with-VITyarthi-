package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.StudentStatus;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Instructor;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.InstructorService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportServiceImpl implements ImportExportService {

    private StudentService studentService;
    private CourseService courseService;
    private InstructorService instructorService;

    public ImportExportServiceImpl(StudentService studentService, CourseService courseService, InstructorService instructorService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @Override
    public void importStudents(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // id,fullName,email,regNo,status
                    int id = Integer.parseInt(data[0]);
                    String fullName = data[1];
                    String email = data[2];
                    String regNo = data[3];
                    StudentStatus status = StudentStatus.valueOf(data[4]);
                    Student student = new Student(id, fullName, email, regNo);
                    student.setStatus(status);
                    studentService.addStudent(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error importing students: " + e.getMessage());
        }
    }

    @Override
    public void exportStudents(String filePath, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                String line = String.format("%d,%s,%s,%s,%s",
                        student.getId(),
                        student.getFullName(),
                        student.getEmail(),
                        student.getRegNo(),
                        student.getStatus().name());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error exporting students: " + e.getMessage());
        }
    }

    @Override
    public void importCourses(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) { // code,title,credits,instructorId,semester,department
                    String code = data[0];
                    String title = data[1];
                    int credits = Integer.parseInt(data[2]);
                    int instructorId = Integer.parseInt(data[3]);
                    Instructor instructor = instructorService.getInstructorById(instructorId);
                    Semester semester = Semester.valueOf(data[4]);
                    String department = data[5];
                    Course course = new Course(code, title, credits, instructor, semester, department);
                    courseService.addCourse(course);
                }
            }
        } catch (IOException e) {
            System.err.println("Error importing courses: " + e.getMessage());
        }
    }

    @Override
    public void exportCourses(String filePath, List<Course> courses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Course course : courses) {
                String instructorId = (course.getInstructor() != null) ? String.valueOf(course.getInstructor().getId()) : "";
                String line = String.format("%s,%s,%d,%s,%s,%s",
                        course.getCode(),
                        course.getTitle(),
                        course.getCredits(),
                        instructorId,
                        course.getSemester().name(),
                        course.getDepartment());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error exporting courses: " + e.getMessage());
        }
    }
}
