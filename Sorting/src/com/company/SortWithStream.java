package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortWithStream {
    public static void main(String[] args) {
        List<Student> students = Student.getStudent();
        System.out.println("Before Sorting");
        System.out.println(students);

        List<Student> students1 = Student.getStudent().stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
        System.out.println("After sorting using Comparator.comparing");
        System.out.println(students1);

        List<Student> students2 = Student.getStudent().stream().sorted((s1,s2)->new Integer(s1.getId()).compareTo(s2.getId())).collect(Collectors.toList());
        System.out.println("After sorting using CompareTo");
        System.out.println(students2);

        List<Student> students3 = Student.getStudent().stream().sorted((s1,s2)->-new Integer(s1.getId()).compareTo(s2.getId())).collect(Collectors.toList());
        System.out.println("After sorting Using Reverse CompareTo");
        System.out.println(students3);
    }
}
