package com.impubudu.exercise8.repository;

import com.impubudu.exercise8.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
