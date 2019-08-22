package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    static void printStudentsWithShortnames(){
        List<Student> students = Student.getStudent();

        for (Student student:students){
            if(student.getName().length()<=6){
                System.out.println(student.getName());
            }
        }
    }

    static void fiterWithStream(){
        List<Student> students = Student.getStudent().stream().filter(s->s.getName().length()<=6).collect(Collectors.toList());
        System.out.println(students);
    }

    static  void process(){
        List<Student> students = Student.getStudent()
                .stream().map(s->new Student("Dr "+s.getName(),s.getId()))
                .collect(Collectors.toList());
        System.out.println(students);
        Student.getStudent()
                .stream().map(s->new Student("Dr "+s.getName(),s.getId()))
                .collect(Collectors.toList()).forEach(s-> System.out.println(s));
    }

    static void count(){
        Long students = Student.getStudent()
                .stream().count();
        System.out.println(students);
    }

    static void max(){
        Student student = Student.getStudent()
                .stream().max(Comparator.comparing(s->s.getId())).get();

        System.out.println(student.getId());
    }

    static void min(){
        Student student = Student.getStudent()
                .stream().min(Comparator.comparing(s->s.getId())).get();

        System.out.println(student.getId());
    }


    public static void main(String[] args) {
        printStudentsWithShortnames();
        fiterWithStream();
        process();
        count();
        max();
        min();
    }
}
