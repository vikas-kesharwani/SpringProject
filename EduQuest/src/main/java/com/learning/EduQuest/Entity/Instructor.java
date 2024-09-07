package com.learning.EduQuest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.DTO.IntructorDetailModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name ="instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;
	
	@Column(name = "first_name")	
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY) //Even after the lazy loading the data will be fetched - answered in modelMapping method
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDet;
	
	@Column(name = "email")
	private String email;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public InstructorDetail getInstructorDet() {
		return instructorDet;
	}

	public void setInstructorDet(InstructorDetail instructorDet) {
		this.instructorDet = instructorDet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public InstructorModel modelMappingwithoutInstDetail() {
		InstructorModel model = new InstructorModel();
		model.setEmail(this.getEmail());
		model.setFirstName(this.getFirstName());
		model.setLastName(this.lastName);
		return model;
	}
	
	public InstructorModel modelMappingwithInstDetail() {
		InstructorModel model = new InstructorModel();
		IntructorDetailModel instDet =  new IntructorDetailModel();
		model.setEmail(this.getEmail());
		model.setFirstName(this.getFirstName());
		model.setLastName(this.lastName);
		
		//Setting instructor detail model explicitly
		if(this.getInstructorDet()!=null)
		instDet = this.getInstructorDet().modelMapping();
		model.setInstructorDetail(instDet);
		
		return model;
	}
	
}
