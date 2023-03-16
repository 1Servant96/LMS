package com.peaksoft.entities.course;

import com.peaksoft.entities.company.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> getAllCourses(Long idOfCompany) {
        return entityManager.createQuery("select h from Course h where h.company.id =: id", Course.class).setParameter("id", idOfCompany).getResultList();
    }

    public void saveCourse(Long idOfCompany, Course course){
        Company company = entityManager.find(Company.class, idOfCompany);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(company);
    }

    public Course getCourseById(Long id){
        return entityManager.find(Course.class, id);

    }
    public void deleteCourseById(Long id){
        entityManager.remove(getCourseById(id));
    }
    public void updateCourse(Long id, Course updatedCourse){
        Course courseToBeUpdated = getCourseById(id);
        courseToBeUpdated.setCourseName(updatedCourse.getCourseName());
        courseToBeUpdated.setDuration(updatedCourse.getDuration());
        courseToBeUpdated.setGroups(updatedCourse.getGroups());
        courseToBeUpdated.setInstructors(updatedCourse.getInstructors());
        courseToBeUpdated.setLessons(updatedCourse.getLessons());
        entityManager.merge(courseToBeUpdated);
    }
}
