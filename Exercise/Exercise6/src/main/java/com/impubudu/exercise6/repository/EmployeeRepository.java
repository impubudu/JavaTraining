package com.impubudu.exercise6.repository;

import com.impubudu.exercise6.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
