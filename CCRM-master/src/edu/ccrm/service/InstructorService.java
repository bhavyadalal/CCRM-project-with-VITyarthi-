package edu.ccrm.service;

import edu.ccrm.domain.Instructor;
import java.util.List;

public interface InstructorService {
    void addInstructor(Instructor instructor);
    Instructor getInstructorById(int id);
    List<Instructor> getAllInstructors();
}
