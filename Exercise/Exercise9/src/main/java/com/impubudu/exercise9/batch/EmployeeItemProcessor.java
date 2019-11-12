package com.impubudu.exercise9.batch;

import com.impubudu.exercise9.model.Employee;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee,Employee> {

    JobExecution jobExecution;

    @Override
    public Employee process(Employee employee) throws Exception {
        Integer count = jobExecution.getExecutionContext().getInt("count");
        count++;
        jobExecution.getExecutionContext().putInt("count",count);
        return employee;
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}
