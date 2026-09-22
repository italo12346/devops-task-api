package com.italo.devopstaskapi.repository;

import com.italo.devopstaskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}