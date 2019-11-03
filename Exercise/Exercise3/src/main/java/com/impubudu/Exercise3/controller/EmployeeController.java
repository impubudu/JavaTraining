package com.impubudu.Exercise3.controller;

import com.impubudu.Exercise3.model.Employee;
import com.impubudu.Exercise3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

//    @RequestMapping(value = "/employees",method = RequestMethod.POST)
//    public Employee updateEmployee(Employee employee){
//        return employeeService.updateEmployee(employee);
//    }

    @RequestMapping(value = "/employees/{id}",method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }

    @RequestMapping(value = "/employees/dept/{id}",method = RequestMethod.GET)
    public List<Employee> getEmployeesofDept(@PathVariable Integer id){
        System.out.println(employeeService.getEmployeesofDept(id));
        return employeeService.getEmployeesofDept(id);
    }

    @RequestMapping(value = "/employees/filter",method = RequestMethod.GET)
    public List<Employee> getFilteredEmployees(){
        return employeeService.getFilteredEmployees();
    }
}
