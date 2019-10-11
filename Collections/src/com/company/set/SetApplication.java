package com.company.set;

import com.company.model.Student;

import java.util.HashSet;
import java.util.Set;

public class SetApplication {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        Student student1 = new Student(3,"kamal","wathatala");
        Student student2 = new Student(3545,"nimal","handala");
        Student student3 = new Student(223,"ayan","kotte");
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student1);
        students.add(student2);
        students.add(new Student(1,"pubudu","galle"));
        students.add(new Student(31,"saman","tangalle"));
        students.add(new Student(14,"kasun","kadawatha"));
        students.add(new Student(1,"pubudu","galle"));
        students.add(new Student(31,"saman","tangalle"));
        students.add(new Student(14,"kasun","kadawatha"));
        System.out.println(students);
    }
}
