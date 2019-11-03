package com.impubudu.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CarStation carStation = (CarStation) context.getBean("carStation");
        carStation.getCar().printCar();
    }
}
