package com.peaksoft.entities.instructor;

import com.peaksoft.entities.course.Course;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InstructorRepositoryImpl implements InstructorRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public List<Instructor> getInstructorList(){
      return   entityManager.createQuery("select a from Instructor a ").getResultList();
    }

    public List<Instructor> getAllInstructors(Long idOfCourse) {
        return entityManager.createQuery("select h from Instructor h where h.course.id = : id", Instructor.class)
                .setParameter("id", idOfCourse).getResultList();
    }

    public void saveInstructor(Long id, Instructor instructor){
        Course course = entityManager.find(Course.class, id);
        course.getInstructors().add(instructor);
        entityManager.merge(instructor);
    }
    public Instructor getInstructorById(Long id){
        return entityManager.find(Instructor.class, id);
    }
    public void deleteInstructorById(Long id){
        entityManager.remove(getInstructorById(id));
    }
    public void updateInstructor(Instructor updatedInstructor, Long id){
        Instructor instructorToBeUpdated = entityManager.find(Instructor.class, id);
        instructorToBeUpdated.setCourse(updatedInstructor.getCourse());
        instructorToBeUpdated.setEmail(updatedInstructor.getEmail());
        instructorToBeUpdated.setSpecialization(updatedInstructor.getSpecialization());
        instructorToBeUpdated.setFirstName(updatedInstructor.getFirstName());
        instructorToBeUpdated.setLastName(updatedInstructor.getLastName());
        instructorToBeUpdated.setPhoneNumber(updatedInstructor.getPhoneNumber());
        entityManager.merge(instructorToBeUpdated);
    }
}
