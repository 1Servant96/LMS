package com.peaksoft.entities.task;

import java.util.List;

public interface TaskRepository {
    List<Task> getAllTasks(Long lessonId);

    void saveTask(Long id, Task task);

    Task getTaskById(Long id);

    void updateTask(Task task, Long id);

    void deleteTaskById(Long id);

}
