package com.impubudu.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class School {

    @Autowired
    @Qualifier("student1")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void printAge(){
        System.out.println(student.getAge());
    }

    public void printName(){
        System.out.println(student.getName());
    }
}
