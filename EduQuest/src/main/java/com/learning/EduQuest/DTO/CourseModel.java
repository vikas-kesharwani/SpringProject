package com.learning.EduQuest.DTO;

import java.util.List;

import com.learning.EduQuest.Entity.Course;

public class CourseModel {

	private int id;

	
	private String title;
	
	private InstructorModel instructor;
	
	private List<ReviewModel> reviews;
	
	public InstructorModel getInstructor() {
		return instructor;
	}

	public void setInstructor(InstructorModel instructor) {
		this.instructor = instructor;
	}

	public List<ReviewModel> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Course createEntity() {
		Course course = new Course();
		course.setTitle(this.getTitle());
		return course;
	}
	
	
}
