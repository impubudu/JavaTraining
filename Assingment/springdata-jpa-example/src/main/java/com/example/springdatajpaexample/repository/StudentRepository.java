package com.example.springdatajpaexample.repository;

import com.example.springdatajpaexample.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
