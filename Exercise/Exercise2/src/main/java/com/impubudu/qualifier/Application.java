package com.impubudu.qualifier;

import com.impubudu.constructorInjection.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        School school = (School) context.getBean("school");
        school.printAge();
        school.printName();
    }
}
