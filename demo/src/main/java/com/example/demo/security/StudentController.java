package com.example.demo.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	public static List<Student> students = Arrays.asList(
			new Student("Saswat", 1),
			new Student("Shivam", 2),
			new Student("Ritesh", 3)
	);
	
	@GetMapping(path = "{studentId}")
	public Student getStudentById(@PathVariable ("studentId") Integer studentId) {
		return students.get(studentId - 1);
	}

}
