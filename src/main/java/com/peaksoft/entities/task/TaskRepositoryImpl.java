package com.peaksoft.entities.task;

import com.peaksoft.entities.lesson.Lesson;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Task> getAllTasks(Long lessonId) {
        return  entityManager.createQuery("select c from Task c where c.lesson.id = :id",Task.class).
                setParameter("id",lessonId).getResultList();
    }
    public void saveTask(Long idOfLesson, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, idOfLesson);
        lesson.getTasks().add(task);
        task.setLesson(lesson);
        entityManager.merge(lesson);
    }
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }
    public void deleteTaskById(Long id) {
        entityManager.remove(getTaskById(id));
    }
    public void updateTask(Task updatedTask, Long id) {
        Task taskToBeUpdated = entityManager.find(Task.class, id);
        taskToBeUpdated.setTaskName(updatedTask.getTaskName());
        taskToBeUpdated.setDeadline(updatedTask.getDeadline());
        entityManager.merge(taskToBeUpdated);
    }
}
