package com.impubudu.exercise11.repository;

import com.impubudu.exercise11.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
