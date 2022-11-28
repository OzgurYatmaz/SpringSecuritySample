package com.sample.controller;

import java.util.ArrayList;
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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/management/api/v1/students")
@SecurityRequirement(name = "Muh2")
public class StudentMangementController {
	
	static List<Student> students;
	static {
		students=new ArrayList<>();
		students.add(new Student(1, "Ebu Bekr bin Kuhafe"));
		students.add(new Student(2, "Muhammad ibn Idrīs"));
		students.add(new Student(3, "Malik bin Enes"));
	}
		    
	@GetMapping
	public List<Student> getAllStudents(){
		return students;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		students.add(student);
		System.out.println(student.getStudentName()+" is added!");
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		try {
			int index=0;
			for(Student s:students) {
				if(s.getStudentId()==studentId) {
					students.remove(index);
					System.out.println(s.getStudentName()+" is removed!");
				}
				index++;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		deleteStudent(studentId);
		students.add(student);
		System.out.println("Student with id "+studentId+" is updated!");
	}
}
