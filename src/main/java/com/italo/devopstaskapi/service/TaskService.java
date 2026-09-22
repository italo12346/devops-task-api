package com.italo.devopstaskapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.italo.devopstaskapi.dto.TaskRequest;
import com.italo.devopstaskapi.exception.TaskNotFoundException;
import com.italo.devopstaskapi.model.Task;
import com.italo.devopstaskapi.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        return taskRepository.save(task);
    }

    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
