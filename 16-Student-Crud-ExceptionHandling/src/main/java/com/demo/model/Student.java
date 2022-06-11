package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int rollno;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private String name;
	private String addres;
	private int age;
	private String divi;
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDivi() {
		return divi;
	}
	public void setDivi(String divi) {
		this.divi = divi;
	}
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", password=" + password + ", name=" + name + ", addres=" + addres
				+ ", age=" + age + ", divi=" + divi + "]";
	}
	
	
}
