package com.impubudu.exercise6.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

@Component
public class JobResultListener implements JobExecutionListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Called beforeJob().");
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Called afterJob().");
    }
}
