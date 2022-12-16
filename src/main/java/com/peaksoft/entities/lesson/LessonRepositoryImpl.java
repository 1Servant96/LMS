package com.peaksoft.entities.lesson;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class LessonRepositoryImpl implements LessonRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Lesson> getAllLessons(Long idOfCourse) {
        return  entityManager.createQuery("select c from Lesson c where c.course.id = :id",Lesson.class)
                .setParameter("id",idOfCourse).getResultList();
    }
    public void saveLesson(Long id, Lesson lesson){

        entityManager.merge(lesson);
    }
    public Lesson getLessonById(Long id){
        return entityManager.find(Lesson.class, id);

    }
    public void deleteLessonById(Long id){
        entityManager.remove(getLessonById(id));
    }
    public void updateLesson(Lesson updatedLesson, Long id){
        Lesson lessonToBeUpdated = entityManager.find(Lesson.class, id);
        lessonToBeUpdated.setLessonName(updatedLesson.getLessonName());
        lessonToBeUpdated.setCourse(updatedLesson.getCourse());
        lessonToBeUpdated.setTasks(updatedLesson.getTasks());
        entityManager.merge(lessonToBeUpdated);
    }
}
