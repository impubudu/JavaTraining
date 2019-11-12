package com.impubudu.exercise9.listeners;

import com.impubudu.exercise9.config.ParameterValidator;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class JobResultListener implements JobExecutionListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Called beforeJob().");
        jobExecution.getExecutionContext().putInt("count", 0);
//        jobExecution.getJobParameters().getLong("time");
        ParameterValidator parameterValidator = new ParameterValidator();
        try {
            parameterValidator.validate(jobExecution.getJobParameters());
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Called afterJob().");
    }
}
