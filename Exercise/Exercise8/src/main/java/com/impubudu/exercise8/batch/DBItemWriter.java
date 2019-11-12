package com.impubudu.exercise8.batch;

import com.impubudu.exercise8.model.Employee;
import com.impubudu.exercise8.repository.EmployeeRepository;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBItemWriter implements ItemWriter<Employee> {

    @Autowired
    EmployeeRepository employeeRepository;

    JobExecution jobExecution;

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
        System.out.println("From Writer ,Updated Job Execution Context Parameter "+jobExecution.getExecutionContext().getInt("count"));
        System.out.println(employees);
        employeeRepository.saveAll(employees);
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}
