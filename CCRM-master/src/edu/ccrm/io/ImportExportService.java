package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import java.util.List;

public interface ImportExportService {
    void importStudents(String filePath);
    void exportStudents(String filePath, List<Student> students);
    void importCourses(String filePath);
    void exportCourses(String filePath, List<Course> courses);
}
