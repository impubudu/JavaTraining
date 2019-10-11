package com.example.employeeservice.controller;

import com.example.employeeservice.model.AssignTask;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Project;
import com.example.employeeservice.model.Task;
import com.example.employeeservice.service.AssignTaskServiceImpl;
import com.example.employeeservice.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ems")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    AssignTaskServiceImpl assignTaskService;

    @RequestMapping(value = "/employees")
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/employees-page")
    @PreAuthorize("hasAuthority('read_profile')")
    public Page<Employee> getEmployeesPage(@RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        return employeeService.getEmployeesPage(PageRequest.of(currentPage - 1, pageSize));
    }

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_manager')")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public Employee getEmployee(@PathVariable Integer id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/employees/{id}/projects",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Project> getProjectIds(@PathVariable Integer id){
        return assignTaskService.getProjects(id);
    }

    @RequestMapping(value = "/employees/{eid}/projects/{pid}/tasks",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('read_profile')")
    public List<Task> getProjectIds(@PathVariable Integer eid, @PathVariable Integer pid){
        return assignTaskService.getTasks(eid,pid);
    }

    @RequestMapping(value = "/assign",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_manager')")
    public List<AssignTask> saveAssignTask(@RequestBody List<AssignTask> assignTasks){
        return assignTaskService.saveAssignTask(assignTasks);
    }
}