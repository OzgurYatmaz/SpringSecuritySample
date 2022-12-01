package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
