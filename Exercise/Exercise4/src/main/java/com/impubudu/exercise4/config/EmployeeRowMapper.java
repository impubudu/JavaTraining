package com.impubudu.exercise4.config;

import com.impubudu.exercise4.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {

    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setBirthDay(resultSet.getDate("birth_day"));
        employee.setAge(resultSet.getInt("age"));
        employee.setAddress(resultSet.getString("address"));
        employee.setSalary(resultSet.getFloat("salary"));
        employee.setMaritalStatus(resultSet.getBoolean("marital_status"));
        employee.setCreatedDate(resultSet.getTimestamp("created_date"));

        return employee;
    }
}
