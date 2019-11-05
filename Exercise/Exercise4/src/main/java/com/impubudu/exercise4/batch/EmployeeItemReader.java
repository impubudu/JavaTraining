//package com.impubudu.exercise4.batch;
//
//import com.impubudu.exercise4.config.CustomJpaNativeQueryProvider;
//import com.impubudu.exercise4.model.Employee;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.beans.factory.annotation.Value;
//
//public class EmployeeItemReader implements ItemReader<Employee> {
//    @Override
//    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//       return new Employee();
//    }
//
//    @Value("#{jobParameters['fileName']}")
//    public void setFileName(final String name) {
//        //...
//    }
//}
