package com.impubudu.Exercise4.batch;

import com.impubudu.Exercise4.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DBItemProcessor implements ItemProcessor<Employee,Employee> {
    @Override
    public Employee process(Employee employee) throws Exception {
        employee.setFirstName("Dr."+employee.getFirstName());
        return employee;
    }
}
