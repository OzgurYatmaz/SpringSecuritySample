package com.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	private Logger logger =Logger.getLogger(StudentMangementController.class);
	
	static List<Student> students;
	static {
		students=new ArrayList<>();
		students.add(new Student(1, "Ebu Bekr bin Kuhafe"));
		students.add(new Student(2, "Muhammad ibn Idrīs"));
		students.add(new Student(3, "Malik bin Enes"));
	}
		    
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents(){
		logger.info("All students are listed!");
		return students;
	}
	
	@PostMapping                 
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		students.add(student);
		logger.info(student.getStudentName()+" is added!");
		System.out.println(student.getStudentName()+" is added!");
	}
	
	@DeleteMapping(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		try {
			int index=0;
			for(Student s:students) {
				if(s.getStudentId()==studentId) {
					students.remove(index);
					System.out.println(s.getStudentName()+" is removed!");
					logger.info(s.getStudentName()+" is removed!");
				}
				index++;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	@PutMapping//(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
//	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
	public void updateStudent( @RequestBody Student student) {
		deleteStudent(student.getStudentId());
		students.add(student);
		System.out.println("Student with id "+student.getStudentId()+" is updated!");
		logger.info("Student with id "+student.getStudentId()+" is updated!");
	}
}
