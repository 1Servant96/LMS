package com.peaksoft.entities.instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> getInstructorList();
    List<Instructor> getAllInstructors(Long courseId);

    void saveInstructor(Long id,Instructor instructor);

    Instructor getInstructorById(Long id);

    void updateInstructor(Instructor instructor, Long id);

    void deleteInstructorById(Long id);
}
