package edu.ccrm.service;

import edu.ccrm.domain.Instructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstructorServiceImpl implements InstructorService {
    private List<Instructor> instructors;

    public InstructorServiceImpl() {
        this.instructors = new ArrayList<>();
    }

    @Override
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    @Override
    public Instructor getInstructorById(int id) {
        Optional<Instructor> instructor = instructors.stream()
                                            .filter(i -> i.getId() == id)
                                            .findFirst();
        return instructor.orElse(null);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return new ArrayList<>(instructors);
    }
}
