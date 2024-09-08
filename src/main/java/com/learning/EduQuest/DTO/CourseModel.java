package com.learning.EduQuest.DTO;

import java.util.List;

import com.learning.EduQuest.Entity.Course;

public class CourseModel {

	private int id;

	
	private String title;
	
	private List<ReviewModel> reviews;
	
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
