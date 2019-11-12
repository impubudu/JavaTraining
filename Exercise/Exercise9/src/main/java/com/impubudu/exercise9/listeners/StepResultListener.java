package com.impubudu.exercise9.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Component
public class StepResultListener {
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Called beforeStep().");
        stepExecution.getJobExecution().getExecutionContext().get("name");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Called afterStep().");
        return ExitStatus.COMPLETED;
    }
}
