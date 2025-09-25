package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    Student getStudentById(int id);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deactivateStudent(int id);
}
