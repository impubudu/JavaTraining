package com.impubudu.exercise8.listeners;

import com.impubudu.exercise8.model.Employee;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

@Component
public class StepSkipListener {
    @OnSkipInRead
    public void onSkipInRead(Throwable t) {
        System.out.println("StepSkipListener - onSkipInRead");
    }

    @OnSkipInWrite
    public void onSkipInWrite(Employee item, Throwable t) {
        System.out.println("StepSkipListener - onSkipInWrite");
    }

    @OnSkipInProcess
    public void onSkipInProcess(Employee item, Throwable t) {
        System.out.println("StepSkipListener - onSkipInProcess");
    }
}
