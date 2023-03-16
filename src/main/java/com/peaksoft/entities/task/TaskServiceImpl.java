package com.peaksoft.entities.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(Long lessonId) {
        return taskRepository.getAllTasks(lessonId);
    }

    @Override
    public void addTask(Long id, Task task) {
        taskRepository.saveTask(id,task);

    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public void updateTask(Task task, Long id) {
        taskRepository.updateTask(task,id);

    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);

    }
}
