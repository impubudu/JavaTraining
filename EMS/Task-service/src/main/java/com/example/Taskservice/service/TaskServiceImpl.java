package com.example.Taskservice.service;

import com.example.Taskservice.exception.TaskNotFoundException;
import com.example.Taskservice.model.Task;
import com.example.Taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl {
    @Autowired
    TaskRepository taskRepository;

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Page<Task> getTasksPage(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task getTask(Integer id){
        Optional<Task> optionalStudent = taskRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new TaskNotFoundException("Task can't find.Id is wrong");
        }
        return optionalStudent.get();
    }

    public List<Task> getTasksList(List<Integer> ids){
        return taskRepository.findByIdIn(ids);
    }
}
