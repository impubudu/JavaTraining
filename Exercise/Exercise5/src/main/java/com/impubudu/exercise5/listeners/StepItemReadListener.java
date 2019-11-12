package com.impubudu.exercise5.listeners;

import com.impubudu.exercise5.model.Employee;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.stereotype.Component;

@Component
public class StepItemReadListener {

    @BeforeRead
    public void beforeRead() {
        System.out.println("ItemReadListener - beforeRead");
    }

    @AfterRead
    public void afterRead(Employee item) {
        System.out.println("ItemReadListener - afterRead");
    }

    @OnReadError
    public void onReadError(Exception ex) {
        System.out.println("ItemReadListener - onReadError");
    }
}
