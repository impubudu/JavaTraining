package com.impubudu.exercise8.config;

import com.impubudu.exercise8.listeners.JobResultListener;
import com.impubudu.exercise8.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
                .faultTolerant()
                .skipPolicy(new NullPointerExceptionSkipper())
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
    @StepScope
    public ItemStreamReader<Employee> employeeItemReader(@Value("#{jobParameters['table']}") String table) throws Exception {

        System.out.println(table+"++++++++++++++++++++++++++++++++++++++++++++++++");

        String sqlQuery = "Select e.id,e.first_name,e.last_name,e.birth_day AS birthDay," +
                "e.age,e.address,e.salary,e.marital_status AS maritalStatus,e.created_date,e.department_id," +
                "d.id AS deptId,d.name AS deptName From " +table +" e  INNER JOIN department d ON e.department_id=d.id";

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
