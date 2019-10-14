package com.example.employeeservice.repository;

import com.impubudu.emscloud.commons.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
