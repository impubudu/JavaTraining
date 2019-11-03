package com.impubudu.Exercise4.batch;

import com.impubudu.Exercise4.model.Employee;
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
    public void beforeJobExecution(JobExecution jobExecution) {
        System.out.print("Listener before Job: ");
        System.out.println(jobExecution.getJobInstance().getJobName());
    }

    @AfterJob
    public void afterJobExecution(JobExecution jobExecution) {
        System.out.print("Listener after Job: ");
        System.out.println(jobExecution.getJobInstance().getJobName());
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

    @AfterRead
    public void afterRead(Employee item){
        System.out.println("After Read Item");
    }

    @AfterProcess
    public void afterProcess(Employee item, Employee result){
        System.out.println("After Process Item");
    }

    @AfterWrite
    public void afterWrite(List<? extends Employee> items){
        System.out.println("After Write");
    }

//    @AfterStep
//    public ExitStatus afterStep(StepExecution stepExecution){
//        System.out.println("After Step");
//    }
    //https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/annotation/package-summary.html#package.description
}
