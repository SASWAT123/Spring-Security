package com.example.demo.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class ManagementController {
	
	public static List<Student> students = new ArrayList<>();
	
	@GetMapping("/instantiate")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public void instantiateStudents(){
		
		Student s1 = new Student("Saswat", 1);
		Student s2 = new Student("Shivam", 2);
		Student s3 = new Student("Ritesh", 3);
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
	}
		
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents(){
		return students;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public List<Student> addStudent(@RequestBody Student student) {
		students.add(student);
		return students;
	}
	
	@DeleteMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public List<Student> deleteStudents(@PathVariable ("studentId") Integer studentId){
		students.remove(studentId - 1);
		return students;
	}

}
