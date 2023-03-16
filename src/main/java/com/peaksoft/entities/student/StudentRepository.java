package com.peaksoft.entities.student;

import java.io.IOException;
import java.util.List;

public interface StudentRepository {
    public List<Student> getStudentList();
    void saveStudent(Student student, Long id);
    void deleteStudentById(Long id);
    Student getStudentById(Long id);
    List<Student> getAllStudents(Long idOfGroup);
//    void addStudentToGroup(Student student, Long id);
    void updateStudent(Student student, Long id);
    void assignStudent(Long groupId, Long studentId) throws IOException;

    }

