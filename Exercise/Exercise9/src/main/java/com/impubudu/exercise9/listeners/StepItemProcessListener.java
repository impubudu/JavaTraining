package com.impubudu.exercise9.listeners;

import com.impubudu.exercise9.model.Employee;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.stereotype.Component;

@Component
public class StepItemProcessListener {
    @BeforeProcess
    public void beforeProcess(Employee item) {
        System.out.println("ItemProcessListener - beforeProcess");
    }

    @AfterProcess
    public void afterProcess(Employee item, Employee result) {
        System.out.println("ItemProcessListener - afterProcess");
    }

    @OnProcessError
    public void onProcessError(Employee item, Exception e) {
        System.out.println("ItemProcessListener - onProcessError");
    }
}
