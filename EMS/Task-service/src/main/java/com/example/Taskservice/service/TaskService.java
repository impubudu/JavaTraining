package com.example.Taskservice.service;

import com.impubudu.emscloud.commons.model.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    public Task save(Task task);

    public List<Task> getTasks();

    public Page<Task> getTasksPage(Pageable pageable);

    public Task getTask(Integer id);

    public List<Task> getTasksList(List<Integer> ids);
}
