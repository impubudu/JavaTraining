package com.impubudu.exercise4.batch;

import com.impubudu.exercise4.model.Employee;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DBItemProcessor implements ItemProcessor<Employee,Employee> {

    JobExecution jobExecution;

    private String threadName;

    @Override
    public Employee process(Employee employee) throws Exception {
        employee.setFirstName("Dr."+employee.getFirstName());
        Integer count = jobExecution.getExecutionContext().getInt("count");
        count++;
        jobExecution.getExecutionContext().putInt("count",count);
        System.out.println(threadName + " processing : "
                + employee.getId());
        return employee;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        jobExecution = stepExecution.getJobExecution();
    }
}
