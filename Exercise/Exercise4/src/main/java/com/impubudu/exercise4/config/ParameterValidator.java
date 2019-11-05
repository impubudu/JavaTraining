package com.impubudu.exercise4.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class ParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        Long time = jobParameters.getLong("time");
        if(time<0) {
            throw new JobParametersInvalidException("Name is not alphabetic");
        }
    }
}
