package com.impubudu.exercise6.batch;

import com.impubudu.exercise6.model.Employee;
import com.impubudu.exercise6.repository.EmployeeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBItemWriter implements ItemWriter<Employee> {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
        System.out.println(employees);
        employeeRepository.saveAll(employees);
    }
}
