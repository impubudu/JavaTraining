package com.example.Taskservice.controller;


import com.example.Taskservice.model.Task;
import com.example.Taskservice.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ems")
public class TaskController {
    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping("/tasks")
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(value = "/tasks-page")
    @PreAuthorize("hasAuthority('read_profile')")
    public Page<Task> getTasksPage(@RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        return taskService.getTasksPage(PageRequest.of(currentPage - 1, pageSize));
    }

    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_manager')")
    public Task save(@RequestBody Task task){
        return taskService.save(task);
    }

    @RequestMapping(value = "/tasks/{ids}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Task> getTask(@PathVariable List<Integer> ids){
        return taskService.getTasksList(ids);
    }
}
