package com.peaksoft.entities.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


    @Override
    public List<Instructor> getInstructorList() {
        return instructorRepository.getInstructorList();
    }

    @Override
    public List<Instructor> getAllInstructors(Long courseId) {
        return instructorRepository.getAllInstructors(courseId);
    }

    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        instructorRepository.saveInstructor(id, instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {
        instructorRepository.updateInstructor(instructor, id);
    }

    @Override
    public void assignInstructor(Long courseId, Long instructorId) throws IOException {
        instructorRepository.assignInstructor(courseId, instructorId);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteInstructorById(id);
    }
}
