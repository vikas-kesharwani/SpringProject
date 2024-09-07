package com.learning.EduQuest.Entity;

import com.learning.EduQuest.DTO.IntructorDetailModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;
	
	@Column(name = "youtube_channel")
	private String youtubeChannel;
	
	@Column(name = "hobby")
	private String hobby;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public IntructorDetailModel modelMapping () {
		IntructorDetailModel model = new IntructorDetailModel();
		model.setHobby(this.getHobby());
		model.setYoutubeChannel(this.getYoutubeChannel());
		return model;
	}
	
}
