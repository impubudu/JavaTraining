package com.example.employeeservice.service;

import com.impubudu.emscloud.commons.model.employee.AssignedOperation;
import com.impubudu.emscloud.commons.model.project.Project;
import com.impubudu.emscloud.commons.model.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public interface AssignedOperationService {
    public List<AssignedOperation> saveAssignedOperation(List<AssignedOperation> assignTasks);

    public List<Project> getProjects(Integer eid);

    public List<Task> getTasks(Integer eid, Integer pid);
}
