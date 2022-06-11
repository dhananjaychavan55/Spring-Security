package com.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Student;
import com.demo.model.StudentDto;
import com.demo.service.StudentService;


@RestController 
@RequestMapping("/StuApp")
@CrossOrigin("*")
public class Controller {

	@Autowired
	private StudentService service;
	
	@Autowired
	private ModelMapper mapper;
	
	 Logger logger=LoggerFactory.getLogger(Controller.class);
	@ GetMapping("/home")
	public String check() {
		logger.info("");
		return "Student App Working";
	}
	
	@PostMapping("/insertStudent")
	public ResponseEntity<String> AddStudent(@RequestBody Student s) {
			String s1=service.saveEmp(s);
		System.out.println(s1);
		 //service.saveEmp(s1);
		//StudentDto dto= mapper.INSTANCE.studentToStudentDto(s1);
		 
		 return new ResponseEntity<String>("Data Save",HttpStatus.CREATED);
   }
	
	@GetMapping("/getAllStu")
	public ResponseEntity<List<StudentDto>> getAllStu(){
		
		List<Student> list = service.getAllStudent();
		List<StudentDto> dtos = list
				  .stream()
				  .map(student -> mapper.map(student, StudentDto.class))
				  .collect(Collectors.toList());
		return new ResponseEntity<List<StudentDto>>(dtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/getSingle/{id}")
	public StudentDto getStudent(@PathVariable int id) {
		
		Student student = service.getStudent(id);
		StudentDto studentDto = mapper.map(student, StudentDto.class);
		return studentDto;
	}
	
	@DeleteMapping("/deletStu/{id}")
	public String deleteStu(@PathVariable int id) {
		
		String string = service.deletStudent(id);
		return string;
	}
	@PutMapping("/updateStu")
	public String updateStudent(@RequestBody Student s) {
		String string = service.updateStudent(s);
		return string;
	}
	
}
