package com.impubudu.exercise5.config;

import com.impubudu.exercise5.listeners.*;
import com.impubudu.exercise5.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
    @Autowired
    EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Employee> itemReader,
                   ItemProcessor<Employee,Employee> itemProcessor,
                   ItemWriter<Employee> itemWriter
    ) throws Exception {

        Step step = stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
//                .listener(new StepItemReadListener())
//                .listener(new StepItemProcessListener())
//                .listener(new StepItemWriteListener())
//                .listener(new StepResultListener())
//                .listener(new StepSkipListener())
//                .listener(new CustomChunkListener())
                .build();
       

        

        return jobBuilderFactory.get("DB-Read")
                .incrementer(new RunIdIncrementer())
                .listener(new JobResultListener())
                .start(step)
                .build();
    }

    @Bean
    public ItemReader<Employee> employeeItemReader() throws Exception {
        JpaPagingItemReader<Employee> reader = new JpaPagingItemReader<Employee>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("select e from Employee e");
        reader.setPageSize(1);
        reader.afterPropertiesSet();
        return reader;
    }

}
