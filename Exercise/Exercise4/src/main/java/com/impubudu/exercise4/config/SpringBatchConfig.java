package com.impubudu.exercise4.config;

import com.impubudu.exercise4.listeners.*;
import com.impubudu.exercise4.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Employee> itemReader,
                   ItemProcessor<Employee,Employee> itemProcessor,
                   ItemWriter<Employee> itemWriter
    ) {

        Step step1 = stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
//                .listener(new StepItemReadListener())
//                .listener(new StepItemProcessListener())
//                .listener(new StepItemWriteListener())
//                .listener(new StepResultListener())
//                .listener(new StepSkipListener())
//                .listener(new CustomChunkListener())
                .faultTolerant()
                .skipPolicy(new NullPointerExceptionSkipper())
                .build();
        Step step2 = stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
//                .listener(new StepItemReadListener())
//                .listener(new StepItemProcessListener())
//                .listener(new StepItemWriteListener())
//                .listener(new StepResultListener())
//                .listener(new StepSkipListener())
//                .listener(new CustomChunkListener())
                .faultTolerant()
                .skipPolicy(new NullPointerExceptionSkipper())
                .build();

        Step step3 = stepBuilderFactory.get("step3")
                .tasklet(reportTasklet())
                .build();

        return jobBuilderFactory.get("DB-Read")
                .incrementer(new RunIdIncrementer())
                .listener(new JobResultListener())
                .flow(step1)
                .next(step2)
                .next(step3)
                .end()
                .build();
    }

    @Bean
    public Tasklet reportTasklet() {
        return null;
    }

    private static final String QUERY_FIND_EMPLOYEE = "SELECT * FROM employee; ";

//    @Bean
//    ItemReader<Employee> databaseItemReader(DataSource dataSource) {
//        JdbcCursorItemReader<Employee> databaseReader = new JdbcCursorItemReader<>();
//
//        databaseReader.setDataSource(dataSource);
//        databaseReader.setSql(QUERY_FIND_EMPLOYEE);
//        databaseReader.setRowMapper(new EmployeeRowMapper());
//
//        return databaseReader;
//    }
//
//    @Bean
//    public ItemReader<Employee> employeeItemReader() throws Exception {
//        JpaPagingItemReader<Employee> reader = new JpaPagingItemReader<Employee>();
//        reader.setEntityManagerFactory(entityManagerFactory);
//        reader.setQueryString("select e from Employee e");
//        reader.setPageSize(1);
//        reader.afterPropertiesSet();
//        return reader;
//    }

    @Bean
    @StepScope
    public ItemStreamReader<Employee> employeeItemReader(@Value("#{jobParameters['time']}") String time) throws Exception {

        System.out.println(time+"++++++++++++++++++++++++++++++++++++++++++++++++");

        String sqlQuery = "Select e.id,e.first_name,e.last_name,e.birth_day AS birthDay," +
                "e.age,e.address,e.salary,e.marital_status AS maritalStatus,e.created_date,e.department_id," +
                "d.id AS deptId,d.name AS deptName From employee e  INNER JOIN department d ON e.department_id=d.id";

        JpaPagingItemReader<Employee> reader = new JpaPagingItemReader<Employee>();

        CustomJpaNativeQueryProvider queryProvider= new CustomJpaNativeQueryProvider();
        queryProvider.setSqlQuery(sqlQuery);
        queryProvider.setEntityClass("EmployeeMapping");
        queryProvider.afterPropertiesSet();

        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(5);
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();
        reader.setSaveState(true);

        return reader;
    }
}
