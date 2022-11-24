package com.sample.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Student;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentMangementController {
	public static List<Student> students=Arrays.asList( new Student(1, "Ebu Bekr Siddik"),
		      new Student(2, "Omer ibn Hattab"),
		      new Student(3, "Ali bin Ebu Talib") );
	
	@GetMapping
	public List<Student> getAllStudents(){
		return students;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println(studentId);
	}
	
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		System.out.println(student);
	}
}
