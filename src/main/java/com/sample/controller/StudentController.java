package com.sample.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Student;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	public static List<Student> students=Arrays.asList( 
			  new Student(1, "Ebu Bekr bin Kuhafe"),
		      new Student(2, "Malik bin Enes"),
		      new Student(3, "Ahmend bin Hanbel") );
	
	@GetMapping(path="{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		for(Student i:students) {
			if(i.getStudentId()==studentId) {
				return i;
			}
		}
		return null;
	}
//	  @GetMapping(path = "{studentId}")
//	    public Student getStudent(@PathVariable("studentId") Integer studentId) {
//	        return STUDENTS.stream()
//	                .filter(student -> studentId.equals(student.getStudentId()))
//	                .findFirst()
//	                .orElseThrow(() -> new IllegalStateException(
//	                        "Student " + studentId + " does not exists"
//	                ));
//	    }

}
