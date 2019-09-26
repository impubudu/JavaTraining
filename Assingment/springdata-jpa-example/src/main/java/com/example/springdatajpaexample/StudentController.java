package com.example.springdatajpaexample;

import com.example.springdatajpaexample.modal.Student;
import com.example.springdatajpaexample.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sms")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(value = "/hello")
    public String greting(){
        return "hello";
    }

//    @RequestMapping(value = "/student",method = RequestMethod.GET)
//    public Student getStudent(){
//
//        Student student = new Student();
//        student.setName("Saman");
//        Address address = new Address();
//        address.setCity("Galle");
//        student.setAddress(address);
//
//        List<Telephone> telephones = new ArrayList<>();
//        Telephone telephone = new Telephone();
//        telephone.setNumber("0122324234234");
//        telephones.add(telephone);
//        student.setTelephones(telephones);
//        telephone.setStudent(student);
//        return student;
//    }

    @RequestMapping(value = "/students",method = RequestMethod.POST)
    public Student save(@RequestBody Student student){
        return studentService.save(student);
    }

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @RequestMapping(value = "/students/{id}",method = RequestMethod.GET)
    public Student getStudent(@PathVariable Integer id){
        return studentService.getStudentById(id);
    }

}
