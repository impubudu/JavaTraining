package com.impubudu.exercise9.repository;

import com.impubudu.exercise9.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
