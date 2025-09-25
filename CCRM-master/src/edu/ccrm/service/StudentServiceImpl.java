package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private List<Student> students;

    public StudentServiceImpl() {
        this.students = new ArrayList<>();
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> student = students.stream()
                                        .filter(s -> s.getId() == id)
                                        .findFirst();
        return student.orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    @Override
    public void deactivateStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setStatus(edu.ccrm.domain.StudentStatus.INACTIVE);
        }
    }
}
