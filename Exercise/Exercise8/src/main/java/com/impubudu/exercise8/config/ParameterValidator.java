package com.impubudu.exercise8.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class ParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        String table = jobParameters.getString("table");
        if(table=="") {
            throw new JobParametersInvalidException("Name is not valid");
        }
    }
}
