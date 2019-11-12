package com.impubudu.exercise11.listeners;

import com.impubudu.exercise11.model.Employee;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StepItemWriteListener {
    @BeforeWrite
    public void beforeWrite(List<? extends Employee> items) {
        System.out.println("ItemWriteListener - beforeWrite");
    }

    @AfterWrite
    public void afterWrite(List<? extends Employee> items) {
        System.out.println("ItemWriteListener - afterWrite");
    }

    @OnWriteError
    public void onWriteError(Exception exception, List<? extends Employee> items) {
        System.out.println("ItemWriteListener - onWriteError");
    }
}
