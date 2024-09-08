package com.learning.EduQuest.DTO;

import com.learning.EduQuest.Entity.Review;

public class ReviewModel {
	
	private int id;
	
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Review createEntity() {
		Review review = new Review();
		review.setComment(this.comment);
		return review;
	}
	
}
