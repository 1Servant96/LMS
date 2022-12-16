package com.peaksoft.entities.student;

import java.util.List;

public interface StudentRepository {

    void saveStudent(Student student, Long id);
    void deleteStudentById(Long id);
    Student getStudentById(Long id);
    List<Student> getAllStudents(Long idOfGroup);
    void addStudentToGroup(Student student, Long id);
    void updateStudent(Student student, Long id);


    }

