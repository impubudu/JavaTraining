package com.impubudu.exercise9.config;

import com.impubudu.exercise9.batch.DBItemProcessor;
import com.impubudu.exercise9.batch.EmployeeItemProcessor;
import com.impubudu.exercise9.listeners.JobResultListener;
import com.impubudu.exercise9.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
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

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Value("file:C:/Users/Pubudu/Downloads/Exercise9/src/main/resources/csv")
    private Resource directory;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   ItemWriter<Employee> itemWriter
    ) throws Exception {

        return jobBuilderFactory.get("DB-Read")
                .incrementer(new RunIdIncrementer())
                .listener(new JobResultListener())
                .flow(step1(itemWriter))
                .next(step2())
                .next(step3(itemWriter))
                .end()
                .build();
    }

    @Bean
    public Step step1 (ItemWriter<Employee> itemWriter) {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(10)
                .reader(itemReader())
                .processor(new DBItemProcessor())
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
    }

    @Bean
    public Step step2() throws Exception {
        return stepBuilderFactory.get("step3")
                .tasklet(fileDeletingTasklet())
                .build();
    }

    @Bean
    public Step step3 (ItemWriter<Employee> itemWriter) throws Exception {
        return stepBuilderFactory.get("step2")
                .<Employee, Employee>chunk(10)
                .reader(employeeItemReader(null))
                .processor(new EmployeeItemProcessor())
                .writer(itemWriter)
                .faultTolerant()
                .skipPolicy(new NullPointerExceptionSkipper())
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Employee> employeeItemReader(@Value("#{jobParameters['table']}") String table) throws Exception {

        System.out.println(table+"++++++++++++++++++++++++++++++++++++++++++++++++");

        String sqlQuery = "Select e.id,e.first_name,e.last_name," +
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

    @Bean
    public FlatFileItemReader<Employee> itemReader() {

        FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("csv/employees.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Employee> lineMapper() {

        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"firstName","lastName","age","address","salary","maritalStatus","departmentId"});

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public FileDeletingTasklet fileDeletingTasklet() throws Exception {
        FileDeletingTasklet tasklet = new FileDeletingTasklet();
        tasklet.setDirectory(directory);
        tasklet.afterPropertiesSet();
        return tasklet;
    }
}