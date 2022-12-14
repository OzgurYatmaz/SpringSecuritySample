package com.sample.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entity.Student;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/students")
@SecurityRequirement(name = "Mock")
public class MockStudentController {
	
	private Logger logger =Logger.getLogger(MockStudentController.class);
	
	public static List<Student> students=Arrays.asList( 
			  new Student(1, "Default Embeded Student"),
		      new Student(2, "Student in Static List") );
	
//	@GetMapping(path="{studentId}")
//	public Student getStudent(@PathVariable("studentId") Integer studentId) {
//		for(Student i:students) {
//			if(i.getStudentId()==studentId) {
//				logger.info(i.getStudentName()+" is retrieved!");
//				return i;
//			}
//		}
//		return null;
//	}
	  @GetMapping(path = "{studentId}")
	    public Student getStudent(@PathVariable("studentId") Integer studentId) {
		  
		  logger.info("Student with id "+studentId+" is tried be retrieved from the static list of the mock service!");
	        return students.stream()
	                .filter(student -> studentId.equals(student.getStudentId()))
	                .findFirst()
	                .orElseThrow(() -> new IllegalStateException(
	                        "Student " + studentId + " does not exists"
	                ));
	    }

}
