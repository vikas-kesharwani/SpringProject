package com.learning.EduQuest.DTO;

import java.util.List;

import com.learning.EduQuest.Entity.Student;


public class StudentModel {
	
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private List<CourseModel> courses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<CourseModel> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseModel> courses) {
		this.courses = courses;
	}
	
	public Student createEntity() {
		Student entity = new Student();
		entity.setEmail(this.email);
		entity.setFirstName(this.firstName);
		entity.setLastName(this.lastName);
		return entity;
	}

}
