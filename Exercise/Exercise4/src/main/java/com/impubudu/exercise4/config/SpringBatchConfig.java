package com.impubudu.exercise4.config;

import com.impubudu.exercise4.batch.DBItemProcessor;
import com.impubudu.exercise4.listeners.*;
import com.impubudu.exercise4.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

    private static final String QUERY_FIND_EMPLOYEE = "SELECT * FROM employee; ";

    @Bean
    ItemReader<Employee> databaseItemReader(DataSource dataSource) {
        JdbcCursorItemReader<Employee> databaseReader = new JdbcCursorItemReader<>();

        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_EMPLOYEE);
        databaseReader.setRowMapper(new EmployeeRowMapper());

        return databaseReader;
    }
}
