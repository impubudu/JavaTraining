package com.impubudu.exercise10.batch;

import com.impubudu.exercise10.model.Employee;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee,Employee> {

    JobExecution jobExecution;

    private String threadName;

	public String getThreadName() {
	  return threadName;
	}

	public void setThreadName(String threadName) {
	  this.threadName = threadName;
	}

    @Override
    public Employee process(Employee employee) throws Exception {
    	System.out.println(threadName + " processing : "
    	        + employee.getDepartmentId());
    	employee.setFirstName("Dr."+employee.getFirstName());
    	System.out.println(employee.getFirstName());
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
