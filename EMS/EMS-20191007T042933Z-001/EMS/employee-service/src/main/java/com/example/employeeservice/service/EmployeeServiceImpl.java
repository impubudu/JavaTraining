package com.example.employeeservice.service;

import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.model.AssignTask;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Project;
import com.example.employeeservice.model.Task;
import com.example.employeeservice.repository.AssignTaskRepostory;
import com.example.employeeservice.repository.EmployeeRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AssignTaskRepostory assignTaskRepostory;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

//    public Page<Employee> getEmployees(Pageable pageable) {
//        return employeeRepository.findAll(pageable);
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<Employee> list;
//
//        if (employees.size() < startItem) {
//            list = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, employees.size());
//            list = employees.subList(startItem, toIndex);
//        }
//
//        Page<Employee> employeePage
//                = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize), employees.size());
//
//        return employeePage;
//    }

    public Employee getEmployee(Integer id){
        Optional<Employee> optionalStudent = employeeRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new EmployeeNotFoundException("Employee can't find.Id is wrong");
        }
        return optionalStudent.get();
    }

    public List<AssignTask> saveAssignTask(List<AssignTask> assignTasks){
        return assignTaskRepostory.saveAll(assignTasks);
    }

    public List<Project> getProjects(Integer eid){
        List<AssignTask> assignTasks = assignTaskRepostory.findByEid(eid);

        String projectIds = assignTasks.stream().map(s->String.valueOf(s.getPid())).collect(Collectors.joining(","));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Project> projectHttpEntity = new HttpEntity<Project>(httpHeaders);
        ResponseEntity<List> responseEntity = restTemplate.exchange("http://localhost:8081/ems/projects/{ids}", HttpMethod.GET, projectHttpEntity, List.class, projectIds);

        return responseEntity.getBody();
    }

    public List<Task> getTasks(Integer pid){
        List<AssignTask> assignTasks = assignTaskRepostory.findByPid(pid);
        String taskIds = assignTasks.stream().map(s->String.valueOf(s.getTid())).collect(Collectors.joining(","));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessTokenConfigurer.getToken());
        HttpEntity<Task> taskHttpEntity = new HttpEntity<Task>(httpHeaders);
        ResponseEntity<List> responseEntity = restTemplate.exchange("http://localhost:8082/ems/tasks/{ids}", HttpMethod.GET, taskHttpEntity, List.class, taskIds);

        return responseEntity.getBody();

    }
}
