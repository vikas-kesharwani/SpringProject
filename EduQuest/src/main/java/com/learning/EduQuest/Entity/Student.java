package com.learning.EduQuest.Entity;

import java.util.ArrayList;
import java.util.List;

import com.learning.EduQuest.DTO.StudentModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name  = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	     name = "course_student",
	     joinColumns = @JoinColumn( name  = "student_id"),
	     inverseJoinColumns = @JoinColumn (name = "course_id"))
	private List<Course> courses;

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public StudentModel modelMapping() {
		StudentModel model = new StudentModel();
		model.setId(this.id);
		model.setEmail(this.email);
		model.setFirstName(this.firstName);
		model.setLastName(this.lastName);
		return model;
	}
	
	public void addCourse(Course course) {
		if(this.courses == null)
			this.courses = new ArrayList<>();
		
		if(this.courses.contains(course))
			return;
		
		this.courses.add(course);
//		course.addStudent(this);
	}
	
	
}
