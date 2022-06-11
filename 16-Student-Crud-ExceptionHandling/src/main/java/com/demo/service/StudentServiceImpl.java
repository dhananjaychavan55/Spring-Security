package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dao.StudentDao;
import com.demo.exception.StudentNotFoundException;
import com.demo.model.Student;
@Service
public class StudentServiceImpl  implements StudentService,UserDetailsService{

	@Autowired
	private StudentDao dao;
	
	@Override
	public String saveEmp(Student s) {
			dao.save(s);
		return "Student Save SuccessFully";
	}

	@Override
	public Student getStudent(int rollno) {
			Optional<Student> id = dao.findById(rollno);
			if (id.isPresent()) {
				return id.get();
			}
			else {
				throw new StudentNotFoundException();
			}
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public String deletStudent(int rollno) {
		System.out.println("Befor Get");
		Optional<Student> optional = dao.findById(rollno);
		if(optional.isPresent()) {
			
			dao.deleteById(rollno);
			return  "Student Delete By id "+rollno;
		}
		    throw new StudentNotFoundException();
	}

	@Override
	public String updateStudent(Student s) {
		Optional<Student> id = dao.findById(s.getRollno());
		
		if(id.isPresent()) {
			dao.save(s);
		return "Student Data Updated";	
		}
		else {
		throw new StudentNotFoundException();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username)  {
		Student student = dao.findByName(username);
		System.out.println(student);
		if(student==null) {
			throw new  UsernameNotFoundException(username);
		}
		return new MyUserPrinciple(student);
	}

}
