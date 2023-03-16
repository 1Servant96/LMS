package com.peaksoft.entities.instructor;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.group.Group;
import com.peaksoft.entities.student.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
public class InstructorRepositoryImpl implements InstructorRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public List<Instructor> getInstructorList(){
      return   entityManager.createQuery("from Instructor", Instructor.class).getResultList();
    }

    public List<Instructor> getAllInstructors(Long courseId) {
        return entityManager.createQuery("select h from Instructor h where h.course.id = : id", Instructor.class)
                .setParameter("id", courseId).getResultList();
    }

    public void saveInstructor(Long id, Instructor instructor){
        Course course = entityManager.find(Course.class, id);
        if (course.getGroups()!=null){
            for (Group group : course.getGroups()) {
                for (Student student: group.getStudents()) {
                    instructor.plus();
                }
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }
    public Instructor getInstructorById(Long id){
        return entityManager.find(Instructor.class, id);
    }
    public void deleteInstructorById(Long id){

        entityManager.remove(getInstructorById(id));
    }
    public void assignInstructor(Long courseId, Long instructorId) throws IOException {
        Instructor instructor = entityManager.find(Instructor.class,instructorId);
        Course course = entityManager.find(Course.class,courseId);

        if (course.getInstructors()!=null){
            for (Instructor i:course.getInstructors()) {
                if (Objects.equals(i.getId(), instructorId)){
                    throw new IOException("we have such instructor");
                }
            }
        }
        for (Group g:instructor.getCourse().getGroups()) {
            for (Student s : g.getStudents()) {
                instructor.minus();
            }
        }
        for (Group g: course.getGroups()) {
            for (Student s : g.getStudents()) {
                instructor.plus();
            }
        }
        instructor.getCourse().getInstructors().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        entityManager.merge(instructor);
        entityManager.merge(course);

    }
    public void updateInstructor(Instructor updatedInstructor, Long id){
        Instructor instructorToBeUpdated = entityManager.find(Instructor.class, id);
        instructorToBeUpdated.setEmail(updatedInstructor.getEmail());
        instructorToBeUpdated.setSpecialization(updatedInstructor.getSpecialization());
        instructorToBeUpdated.setFirstName(updatedInstructor.getFirstName());
        instructorToBeUpdated.setLastName(updatedInstructor.getLastName());
        instructorToBeUpdated.setPhoneNumber(updatedInstructor.getPhoneNumber());
        entityManager.merge(instructorToBeUpdated);
    }
}
