package com.impubudu.exercise3.repository;

import com.impubudu.exercise3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>, QuerydslPredicateExecutor<Employee> {
    @Query(value = "Select  e  From Employee e  INNER JOIN Department d ON e.department.id=d.id where d.id=:deptId")
    List<Employee> getEmployeesofDept(@Param("deptId") Integer deptId);
}
