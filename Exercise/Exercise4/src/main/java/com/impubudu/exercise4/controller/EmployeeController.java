package com.impubudu.exercise4.controller;


import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping("/load")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {


        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }

    public static int[] result(int[] array){
        int k = 1;
        int[] result = new int[array.length];
        for (int i=0;i<array.length;i++){
            if (k == 0) {
                int j = 0;
                while (i - j - 1 >= 0) {
                    if (array[i - j] < array[i - j - 1]) {
                        result[i - j - 1] += 1;
                    } else {
                        break;
                    }
                    j += 1;
                }
                k = 1;
            }
            result[i]=k;
            if (i+1 < array.length && array[i] < array[i+1]){
                k += 1;
            }
            else{
                if (k > 1){
                    k = 1;
                }else{
                    k = 0;
                }
            }
        }
        return result;
    }
}

