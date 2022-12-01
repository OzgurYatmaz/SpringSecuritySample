package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.entity.Student;
import com.sample.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> getStudents() {

		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(int theId) {
		return studentRepository.getReferenceById(theId);
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(int theId) {
		studentRepository.deleteById(theId);
	}

	@Override
	public void updateStudent(Student student) {
	//	studentRepository.u
	}

}
