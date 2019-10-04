package com.example.Taskservice.controller;


import com.example.Taskservice.model.Task;
import com.example.Taskservice.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ems")
public class TaskController {
    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    public Task save(@RequestBody Task task){
        return taskService.save(task);
    }
}
