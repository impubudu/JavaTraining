package com.example.springdatajpaexample.service;

import com.example.springdatajpaexample.modal.Student;
import com.example.springdatajpaexample.modal.Telephone;
import com.example.springdatajpaexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl {

    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student){

        for (Telephone telephone:student.getTelephones()){
            telephone.setStudent(student);
        }
        return studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.orElseThrow(()-> new UsernameNotFoundException("student id is wrong"));
        return optionalStudent.get();
    }

}
