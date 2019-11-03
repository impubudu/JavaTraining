package com.impubudu.Exercise4.config;

import com.impubudu.Exercise4.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Employee> itemReader,
                   ItemProcessor<Employee,Employee> itemProcessor,
                   ItemWriter<Employee> itemWriter
    ) {

        Step step = stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("DB-Read")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    private static final String QUERY_FIND_EMPLOYEE = "SELECT * FROM employee; ";

    @Bean
    ItemReader<Employee> databaseXmlItemReader(DataSource dataSource) {
        JdbcCursorItemReader<Employee> databaseReader = new JdbcCursorItemReader<>();

        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_EMPLOYEE);
        databaseReader.setRowMapper(new EmployeeRowMapper());

        return databaseReader;
    }
}
