package com.example.demo.security;

public class Student {
	
	private String studentName;
	private Integer studentId;
	
	public Student(String studentName, Integer studentId) {
		this.studentName = studentName;
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
}
