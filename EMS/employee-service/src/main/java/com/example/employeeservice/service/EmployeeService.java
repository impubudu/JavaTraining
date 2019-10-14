package com.example.employeeservice.service;

import com.impubudu.emscloud.commons.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);

    public List<Employee> getEmployees();

    public Page<Employee> getEmployeesPage(Pageable pageable);

    public Employee getEmployee(Integer id);
}
