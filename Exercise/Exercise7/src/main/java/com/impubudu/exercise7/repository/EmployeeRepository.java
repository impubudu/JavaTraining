package com.impubudu.exercise7.repository;

import com.impubudu.exercise7.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
