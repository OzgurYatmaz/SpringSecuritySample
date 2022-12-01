package com.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	@Id
	@Column(name="studentId", insertable=true, updatable=true, unique=true, nullable=false)
    private int studentId;
	
	@Column(name="studentName")
    private  String studentName;

	public Student() {
	}

	public Student(int id, String name) {
		this.studentId=id;
		this.studentName=name;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

  
 
    
    
}