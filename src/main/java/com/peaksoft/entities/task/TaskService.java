package com.peaksoft.entities.task;

import org.springframework.stereotype.Service;

import java.util.List;
public interface TaskService {
    List<Task> getAlTasks(Long lessonId);

    void addTask(Long id, Task task);

    Task getTaskById(Long id);

    void updateTask(Task task, Long id);

    void deleteTask(Long id);

}
