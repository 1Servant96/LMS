package com.peaksoft.entities.student;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    public void deleteStudentById(Long id){
        studentRepository.deleteStudentById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents(Long idOfGroup) {
        return studentRepository.getAllStudents(idOfGroup);
    }

    @Override
    public void addStudentToGroup(Student student, Long idOfGroup) {
    studentRepository.addStudentToGroup(student, idOfGroup);
    }

    public void saveStudent(Student student, Long idOfGroup){
        studentRepository.saveStudent(student, idOfGroup);
    }
    public void updateStudent(Student updatedStudent, Long id){
        studentRepository.updateStudent(updatedStudent, id);
    }
}
