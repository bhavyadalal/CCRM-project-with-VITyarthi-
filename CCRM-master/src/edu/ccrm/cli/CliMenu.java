package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.BackupServiceImpl;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.ImportExportServiceImpl;
import edu.ccrm.service.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CliMenu {
    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;
    private TranscriptService transcriptService;
    private ImportExportService importExportService;
    private BackupService backupService;
    private Scanner scanner;
    private AppConfig appConfig;

    public CliMenu() {
        appConfig = AppConfig.getInstance();
        studentService = new StudentServiceImpl();
        InstructorService instructorService = new InstructorServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl();
        transcriptService = new TranscriptServiceImpl(enrollmentService);
        importExportService = new ImportExportServiceImpl(studentService, courseService, instructorService);
        backupService = new BackupServiceImpl();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageEnrollmentAndGrades();
                    break;
                case 4:
                    handleFileOperations();
                    break;
                case 5:
                    System.out.println("Exiting CCRM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void printMenu() {
        System.out.println("\n--- CCRM Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment & Grades");
        System.out.println("4. File Operations (Import/Export/Backup)");
        System.out.println("5. Exit");
    }

    private void manageStudents() {
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch(choice){
                case 1:
                    addStudent();
                    break;
                case 2:
                    listAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deactivateStudent();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter student Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter student Registration Number: ");
        String regNo = scanner.nextLine();

        Student student = new Student(id, fullName, email, regNo);
        studentService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        if (studentService.getAllStudents().isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        for (Student student : studentService.getAllStudents()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getFullName() + ", RegNo: " + student.getRegNo() + ", Status: " + student.getStatus());
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student studentToUpdate = studentService.getStudentById(id);

        if (studentToUpdate == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Full Name (current: " + studentToUpdate.getFullName() + "): ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()) {
            studentToUpdate.setFullName(newFullName);
        }

        System.out.print("Enter new Email (current: " + studentToUpdate.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            studentToUpdate.setEmail(newEmail);
        }

        System.out.print("Enter new Registration Number (current: " + studentToUpdate.getRegNo() + "): ");
        String newRegNo = scanner.nextLine();
        if (!newRegNo.isEmpty()) {
            studentToUpdate.setRegNo(newRegNo);
        }

        studentService.updateStudent(studentToUpdate);
        System.out.println("Student updated successfully!");
    }

    private void deactivateStudent() {
        System.out.print("Enter student ID to deactivate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated successfully (if found).");
    }

    private void manageCourses() {
        int choice;
        do {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Assign Instructor to Course");
            System.out.println("5. Search Courses");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    assignInstructorToCourse();
                    break;
                case 5:
                    searchCourses();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void addCourse() {
        System.out.print("Enter course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Instructor ID (0 if none): ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();
        Instructor instructor = null;
        if (instructorId != 0) {
            // Assuming instructorService is available in CliMenu
            // For now, we'll just create a dummy instructor if not found
            instructor = new Instructor(instructorId, "Dummy Instructor", "dummy@example.com");
        }
        System.out.print("Enter Semester (FALL, SPRING, SUMMER): ");
        Semester semester = Semester.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Course course = new Course(code, title, credits, instructor, semester, department);
        courseService.addCourse(course);
        System.out.println("Course added successfully!");
    }

    private void listAllCourses() {
        System.out.println("\n--- All Courses ---");
        if (courseService.getAllCourses().isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : courseService.getAllCourses()) {
            String instructorName = (course.getInstructor() != null) ? course.getInstructor().getFullName() : "N/A";
            System.out.println("Code: " + course.getCode() + ", Title: " + course.getTitle() + ", Credits: " + course.getCredits() + ", Instructor: " + instructorName + ", Semester: " + course.getSemester() + ", Department: " + course.getDepartment());
        }
    }

    private void updateCourse() {
        System.out.print("Enter course Code to update: ");
        String code = scanner.nextLine();
        Course courseToUpdate = courseService.getCourseByCode(code);

        if (courseToUpdate == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new Title (current: " + courseToUpdate.getTitle() + "): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            courseToUpdate.setTitle(newTitle);
        }

        System.out.print("Enter new Credits (current: " + courseToUpdate.getCredits() + "): ");
        String newCreditsStr = scanner.nextLine();
        if (!newCreditsStr.isEmpty()) {
            courseToUpdate.setCredits(Integer.parseInt(newCreditsStr));
        }

        System.out.print("Enter new Department (current: " + courseToUpdate.getDepartment() + "): ");
        String newDepartment = scanner.nextLine();
        if (!newDepartment.isEmpty()) {
            courseToUpdate.setDepartment(newDepartment);
        }

        courseService.updateCourse(courseToUpdate);
        System.out.println("Course updated successfully!");
    }

    private void assignInstructorToCourse() {
        System.out.print("Enter course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Instructor ID to assign: ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();

        // Assuming instructorService is available in CliMenu
        Instructor instructor = new Instructor(instructorId, "Dummy Instructor", "dummy@example.com"); // Replace with actual lookup
        courseService.assignInstructor(courseCode, instructor);
        System.out.println("Instructor assigned successfully (if course found).");
    }

    private void searchCourses() {
        System.out.print("Enter search query (leave blank for all): ");
        String query = scanner.nextLine();
        query = query.isEmpty() ? null : query;

        System.out.print("Enter Department (leave blank for all): ");
        String department = scanner.nextLine();
        department = department.isEmpty() ? null : department;

        Semester semester = null;
        System.out.print("Enter Semester (FALL, SPRING, SUMMER, leave blank for all): ");
        String semesterStr = scanner.nextLine();
        if (!semesterStr.isEmpty()) {
            semester = Semester.valueOf(semesterStr.toUpperCase());
        }

        System.out.println("\n--- Search Results ---");
        List<Course> results = courseService.searchCourses(query, department, semester);
        if (results.isEmpty()) {
            System.out.println("No courses found matching criteria.");
            return;
        }
        for (Course course : results) {
            String instructorName = (course.getInstructor() != null) ? course.getInstructor().getFullName() : "N/A";
            System.out.println("Code: " + course.getCode() + ", Title: " + course.getTitle() + ", Credits: " + course.getCredits() + ", Instructor: " + instructorName + ", Semester: " + course.getSemester() + ", Department: " + course.getDepartment());
        }
    }

    private void manageEnrollmentAndGrades() {
        int choice;
        do {
            System.out.println("\n--- Enrollment & Grades Management ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Record Grade");
            System.out.println("4. View Student Transcript");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enrollStudentInCourse();
                    break;
                case 2:
                    unenrollStudentFromCourse();
                    break;
                case 3:
                    recordGrade();
                    break;
                case 4:
                    viewStudentTranscript();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void enrollStudentInCourse() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        enrollmentService.enrollStudent(student, course);
        System.out.println("Student enrolled successfully!");
    }

    private void unenrollStudentFromCourse() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or Course not found.");
            return;
        }

        enrollmentService.unenrollStudent(student, course);
        System.out.println("Student unenrolled successfully!");
    }

    private void recordGrade() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Grade (S, A, B, C, D, F): ");
        Grade grade = Grade.valueOf(scanner.nextLine().toUpperCase());

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or Course not found.");
            return;
        }

        enrollmentService.recordGrade(student, course, grade);
        System.out.println("Grade recorded successfully!");
    }

    private void viewStudentTranscript() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(transcriptService.generateTranscript(student));
    }

    private void handleFileOperations() {
        int choice;
        do {
            System.out.println("\n--- File Operations ---");
            System.out.println("1. Import Students");
            System.out.println("2. Export Students");
            System.out.println("3. Import Courses");
            System.out.println("4. Export Courses");
            System.out.println("5. Create Backup");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String dataFolderPath = appConfig.getDataFolderPath();

            switch (choice) {
                case 1:
                    System.out.print("Enter file path for students import (e.g., " + dataFolderPath + "/students.csv): ");
                    String studentImportPath = scanner.nextLine();
                    importExportService.importStudents(studentImportPath);
                    System.out.println("Students imported successfully!");
                    break;
                case 2:
                    System.out.print("Enter file path for students export (e.g., " + dataFolderPath + "/students.csv): ");
                    String studentExportPath = scanner.nextLine();
                    importExportService.exportStudents(studentExportPath, studentService.getAllStudents());
                    System.out.println("Students exported successfully!");
                    break;
                case 3:
                    System.out.print("Enter file path for courses import (e.g., " + dataFolderPath + "/courses.csv): ");
                    String courseImportPath = scanner.nextLine();
                    importExportService.importCourses(courseImportPath);
                    System.out.println("Courses imported successfully!");
                    break;
                case 4:
                    System.out.print("Enter file path for courses export (e.g., " + dataFolderPath + "/courses.csv): ");
                    String courseExportPath = scanner.nextLine();
                    importExportService.exportCourses(courseExportPath, courseService.getAllCourses());
                    System.out.println("Courses exported successfully!");
                    break;
                case 5:
                    try {
                        backupService.createBackup(dataFolderPath, "CCRM/backups");
                        System.out.println("Backup created successfully!");
                    } catch (IOException e) {
                        System.err.println("Error creating backup: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }
}