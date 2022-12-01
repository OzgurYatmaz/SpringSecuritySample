package com.sample.service;

import java.util.List;

import com.sample.entity.Student;

public interface StudentService {

	public List<Student> getStudents();
	
	public Student getStudent(int theId);

	public void saveStudent(Student student);

	public void deleteStudent(int theId);
	
	public void updateStudent(Student student);
}
