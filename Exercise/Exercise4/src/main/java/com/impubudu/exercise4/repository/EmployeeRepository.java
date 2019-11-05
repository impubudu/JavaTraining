package com.impubudu.exercise4.repository;

import com.impubudu.exercise4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
