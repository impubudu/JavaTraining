package com.example.employeeservice.service;

import com.example.employeeservice.config.AccessTokenConfigurer;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.model.AssignTask;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Project;
import com.example.employeeservice.model.Task;
import com.example.employeeservice.repository.AssignTaskRepository;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplateEmployee(){
        return new RestTemplate();
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Page<Employee> getEmployeesPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployee(Integer id){
        Optional<Employee> optionalStudent = employeeRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new EmployeeNotFoundException("Employee can't find.Id is wrong");
        }
        return optionalStudent.get();
    }
}
