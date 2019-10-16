package com.example.Taskservice.repository;

import com.example.Taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByIdIn(List<Integer> id);
}
