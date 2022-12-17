package com.sample.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entity.Student;
import com.sample.service.StudentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/management/api/v1/students")
@SecurityRequirement(name = "Real")//for openapi authentication
public class StudentMangementController {
	
	private Logger logger =Logger.getLogger(StudentMangementController.class);
	
	@Autowired
	StudentService studentService;
		    
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents(){
		List<Student> students=null;
		try {
			logger.info("All students are listed!");
			students = studentService.getStudents();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return students;
	}
	
	@PostMapping                 
	@PreAuthorize("hasAuthority('student:write')")
	public ResponseEntity<String> registerNewStudent(@RequestBody Student student) {
		try {
			studentService.saveStudent(student);
			logger.info(student.getStudentName()+" is added!");
			System.out.println(student.getStudentName()+" is added!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return ResponseEntity.ok("Student "+student.getStudentName()+" is added to DB");
	}
	
	@DeleteMapping(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public ResponseEntity<String>  deleteStudent(@PathVariable("studentId") Integer studentId) {
		try {
			studentService.deleteStudent(studentId);
			logger.info("Student with id "+studentId+" is deleted!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return ResponseEntity.ok("Student with id "+studentId+" is deleted!");
	}
	
	@PutMapping//(path="{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
//	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
	public ResponseEntity<String> updateStudent( @RequestBody Student student) {
		try {
			deleteStudent(student.getStudentId());
			registerNewStudent(student);
			System.out.println("Student with id "+student.getStudentId()+" is updated!");
			logger.info("Student with id "+student.getStudentId()+" is updated!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		
		return ResponseEntity.ok("Student with id "+student.getStudentId()+" is updated!");
	}
}
