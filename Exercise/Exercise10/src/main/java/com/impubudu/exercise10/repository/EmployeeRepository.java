package com.impubudu.exercise10.repository;

import com.impubudu.exercise10.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
