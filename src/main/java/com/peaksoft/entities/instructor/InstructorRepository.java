package com.peaksoft.entities.instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorRepository {
    List<Instructor> getInstructorList();
    List<Instructor> getAllInstructors(Long courseId);

    void saveInstructor(Long id,Instructor instructor);

    Instructor getInstructorById(Long id);

    void updateInstructor(Instructor instructor, Long id);

    void deleteInstructorById(Long id);

//    void assignInstructor(Long courseId,Long instructorId) throws IOException;
}
