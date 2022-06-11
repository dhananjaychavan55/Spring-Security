package com.demo.service;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.SortDto;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.model.Student;

public interface StudentService {

	public String saveEmp(Student s);
	public Student getStudent(int rollno);
	public List<Student> getAllStudent();
	public String deletStudent(int rollno);
	public String updateStudent(Student s);
	UserDetails loadUserByUsername(String username);
}
