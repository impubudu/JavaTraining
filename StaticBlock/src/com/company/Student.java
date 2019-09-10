package com.company;

public class Student {
    Student(){
        System.out.println("constructor");
    }
    static {
        System.out.println("static block");
    }

    {
        System.out.println("non static block");
    }

    public void print(String s){
        System.out.println(s);
    }
}
