package com.learning.EduQuest.DTO;

import com.learning.EduQuest.Entity.InstructorDetail;

public class IntructorDetailModel {
	
	private String hobby;
	
	private String youtubeChannel;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}
	
	public InstructorDetail createEntityMapping() {
		InstructorDetail instDet = new InstructorDetail();
		instDet.setHobby(this.getHobby());
		instDet.setYoutubeChannel(this.getYoutubeChannel());
		return instDet;
	}

}
