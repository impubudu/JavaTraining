package com.impubudu.Exercise3.repository;

import com.impubudu.Exercise3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>, QuerydslPredicateExecutor<Employee> {
    @Query(value = "Select  *  From employee e  INNER JOIN department d ON e.department_id=d.id where d.id=:deptId", nativeQuery = true)
    List<Employee> getEmployeesofDept(@Param("deptId") Integer deptId);
}
