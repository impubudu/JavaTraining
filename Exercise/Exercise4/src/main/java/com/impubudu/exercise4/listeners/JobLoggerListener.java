package com.impubudu.exercise4.listeners;

import com.impubudu.exercise4.model.Employee;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobLoggerListener {
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Called beforeJob().");
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Called afterJob().");
    }

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

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Called beforeStep().");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Called afterStep().");
        return ExitStatus.COMPLETED;
    }

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

    @BeforeChunk
    public void beforeChunk(ChunkContext context){
        System.out.println("Before Chunk");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context){
        System.out.println("After Chunk");
    }

    @AfterChunkError
    public void afterFailedChunk(ChunkContext context){
        System.out.println("After Chunk Error");
    }
}
