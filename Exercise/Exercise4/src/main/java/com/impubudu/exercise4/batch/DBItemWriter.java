package com.impubudu.exercise4.batch;

import com.impubudu.exercise4.model.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBItemWriter implements ItemWriter<Employee> {

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
        System.out.println(employees);
    }
}
