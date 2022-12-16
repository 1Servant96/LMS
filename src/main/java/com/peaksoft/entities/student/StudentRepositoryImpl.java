package com.peaksoft.entities.student;

import com.peaksoft.entities.group.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Student> getAllStudents(Long idOfGroup) {
        return  entityManager.createQuery("select c from Student c where c.group.id = :id", Student.class)
                .setParameter("id",idOfGroup).getResultList();
    }

    public List<Student> getStudentList() {
        return entityManager.createQuery("select s from Student s" , Student.class).getResultList();
    }
    public void addStudentToGroup(Student student, Long id) {
        Group group = entityManager.find(Group.class,id);
        student.setGroup(group);
        student.addStudentToGroup(student);
        entityManager.merge(student);
    }

    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }
    public void saveStudent(Student student, Long Id){

    }

    public void updateStudent(Student updatedStudent, Long id) {
        Student studentToBeUpdated = entityManager.find(Student.class,id);
        studentToBeUpdated.setFirstName(updatedStudent.getFirstName());
        studentToBeUpdated.setLastName(updatedStudent.getLastName());
        studentToBeUpdated.setPhoneNumber(updatedStudent.getPhoneNumber());
        studentToBeUpdated.setEmail(updatedStudent.getEmail());
        studentToBeUpdated.setStudyFormat(updatedStudent.getStudyFormat());
        studentToBeUpdated.setGroup(updatedStudent.getGroup());
        entityManager.merge(studentToBeUpdated);
    }

    public void deleteStudentById(Long id) {
       entityManager.remove(getStudentById(id));
    }

//    public void assignStudent(Long groupId, Long studentId) throws IOException {
//        Group group = entityManager.find(Group.class,groupId);
//        Student student = entityManager.find(Student.class,studentId);
//        if (group.getStudentList()!=null){
//            for (Student i:group.getStudentList()) {
//                if (i.getId()==studentId){
//                    throw new IOException("we have such student");
//                }
//            }
//        }
//        student.getGroup().getStudentList().remove(student);
//
//        student.setGroup(group);
//        group.addStudent(student);
//        entityManager.merge(student);
//
//
//
//    }
}