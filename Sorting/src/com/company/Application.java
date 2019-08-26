package com.company;

import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Student> students = Student.getStudent();
        System.out.println("Before Sort");
        System.out.println(students);

        Collections.sort(students,new StudentRank());
        System.out.println("After Sort");
        System.out.println(students);

        List<StudentWithComparable> studentWithComparables = StudentWithComparable.getStudent();
        System.out.println("Before Sort studentWithComparables");
        System.out.println(studentWithComparables);

        Collections.sort(studentWithComparables);
        System.out.println("After Sort studentWithComparables");
        System.out.println(students);
    }
}
