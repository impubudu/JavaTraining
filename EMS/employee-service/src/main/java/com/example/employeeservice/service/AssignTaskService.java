package com.example.employeeservice.service;

import com.impubudu.emscloud.commons.model.AssignTask;
import com.impubudu.emscloud.commons.model.Project;
import com.impubudu.emscloud.commons.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public interface AssignTaskService {
    public List<AssignTask> saveAssignTask(List<AssignTask> assignTasks);

    public List<Project> getProjects(Integer eid);

    public List<Task> getTasks(Integer eid, Integer pid);
}
