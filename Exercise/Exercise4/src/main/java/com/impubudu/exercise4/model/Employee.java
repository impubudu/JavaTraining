package com.impubudu.exercise4.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@SqlResultSetMapping(
        name = "EmployeeMapping",
        entities = {
                @EntityResult(
                        entityClass = Employee.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "firstName", column = "first_name"),
                                @FieldResult(name = "lastName", column = "last_name"),
                                @FieldResult(name = "birthDay", column = "birthDay"),
                                @FieldResult(name = "age", column = "age"),
                                @FieldResult(name = "address", column = "address"),
                                @FieldResult(name = "salary", column = "salary"),
                                @FieldResult(name = "maritalStatus", column = "maritalStatus"),
                                @FieldResult(name = "departmentId", column = "department_id"),
                                @FieldResult(name = "createdDate", column = "created_date")}),
                })

//@EntityResult(
//        entityClass = Department.class,
//        fields = {
//                @FieldResult(name = "id", column = "deptId"),
//                @FieldResult(name = "name", column = "deptName")})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private int age;

    private String address;

    private float salary;

    private boolean maritalStatus;

    @CreationTimestamp
    private Timestamp createdDate;

//    @ManyToOne
//    @JoinColumn
//    private Department department;

    private int departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", maritalStatus=" + maritalStatus +
                ", createdDate=" + createdDate +
                '}';
    }
}
