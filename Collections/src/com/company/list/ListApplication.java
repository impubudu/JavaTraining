package com.company.list;

import com.company.list.CustomSortingById;
import com.company.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListApplication {

    public static void main(String[] args) {
	    List<String> names = new ArrayList<>();
	    names.add("pubudu");
        names.add("sandun");
        names.add("thilina");
        names.add("gayan");
        names.add("saman");
        names.add("nalin");
        names.add("deshan");
        names.add("dihara");
        names.add("prasanna");
        names.add("chethana");
        System.out.println(names);
        Collections.sort(names);
        System.out.println(names);


        List<Student> students = new ArrayList<>();
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
        students.add(new Student(51,"chethana","jaella"));
        students.add(new Student(61,"thilina","negambo"));
        students.add(new Student(13,"dihara","weeraketiya"));
        students.add(new Student(18,"saman","colombo"));
        students.add(new Student(123,"deshan","mathara"));
        students.add(new Student(11,"prasanna","habarana"));
        students.add(new Student(7,"nalin","horana"));

        System.out.println(students);

        Collections.sort(students,new CustomSortingById());

        System.out.println(students);

        Collections.sort(students);

        System.out.println(students);
    }
}
