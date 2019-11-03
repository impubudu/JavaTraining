package com.impubudu.Exercise3.service;

import com.impubudu.Exercise3.model.Employee;
import com.impubudu.Exercise3.model.QEmployee;
import com.impubudu.Exercise3.repository.EmployeeRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesofDept(Integer id){
        return employeeRepository.getEmployeesofDept(id);
    }

    public List<Employee> getFilteredEmployees(){
        List<Employee> employees = new ArrayList<>();
        BooleanExpression booleanExpression = QEmployee.employee.age.goe(30).and(QEmployee.employee.maritalStatus.eq(true));
        Iterable<Employee> employeeIterable = employeeRepository.findAll(booleanExpression);
        employeeIterable.forEach(employees::add);
        return employees;
    }
}
