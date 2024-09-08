package com.learning.EduQuest.DTO;

import java.util.List;

import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.InstructorDetail;

public class InstructorModel {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private IntructorDetailModel instructorDetail;
	
	private List<CourseModel> courses;

	public List<CourseModel> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseModel> courses) {
		this.courses = courses;
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

	
	public IntructorDetailModel getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(IntructorDetailModel instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public Instructor createEntityMapping() { 
		Instructor instructor =  new Instructor ();
		InstructorDetail instructorDetail = new InstructorDetail ();
		instructor.setEmail(this.getEmail());
		instructor.setFirstName(this.getFirstName());
		instructor.setLastName(this.getLastName());
		
		//to handle case where only instructor needs to be saved with out instructor details
		if (this.instructorDetail !=null) {
			instructorDetail =this.instructorDetail.createEntityMapping();
			instructor.setInstructorDet(instructorDetail);
		}
		
		
		return instructor;
		
	}
}
