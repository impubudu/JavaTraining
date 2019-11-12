package com.impubudu.exercise6.batch;

import com.impubudu.exercise6.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DBItemProcessor implements ItemProcessor<Employee,Employee> {

    @Override
    public Employee process(Employee employee) throws Exception {
    	
        employee.setSalary(30000.0f+employee.getSalary());
        return employee;
    }
}
