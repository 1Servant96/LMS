package com.peaksoft.entities.task;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.lesson.Lesson;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Task> getAllTasks(Long idOfLesson) {
        return entityManager.createQuery("select h from Task h", Task.class).getResultList();
    }
    public void saveTask(Long idOfLesson, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, idOfLesson);
        lesson.getTasks().add(task);
        entityManager.merge(lesson);
        entityManager.merge(task);
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
        taskToBeUpdated.setLesson(updatedTask.getLesson());
        entityManager.merge(taskToBeUpdated);
    }
}
