package com.company;

import java.util.ArrayList;
import java.util.List;

public class StudentWithComparable implements Comparable<StudentWithComparable>{

    private  String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentWithComparable(String name, int id){
        this.name=name;
        this.id=id;
    }

    public  static List<StudentWithComparable> getStudent(){
        List<StudentWithComparable> students = new ArrayList<>();
        students.add(new StudentWithComparable("Krish",1));
        students.add(new StudentWithComparable("Hasini",22));
        students.add(new StudentWithComparable("Bhagya",311));
        students.add(new StudentWithComparable("Suranga",12));
        students.add(new StudentWithComparable("Nuwan",2));
        students.add(new StudentWithComparable("Praveen",133));
        students.add(new StudentWithComparable("Shehanza",34));
        return students;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(StudentWithComparable s) {
        if(id<s.getId()){
            return -1;
        }else if (id>s.getId()){
            return 1;
        }else {
            return 0;
        }
    }
}
