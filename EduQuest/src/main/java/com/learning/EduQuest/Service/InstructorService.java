package com.learning.EduQuest.Service;

import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.Entity.Instructor;

public interface InstructorService {

	String save(Instructor instructor);
	
	InstructorModel findInstructorByID(int Id) throws Exception;

	InstructorModel findInstructorwithInstructorDet(int Id) throws Exception;
	
	InstructorModel findInstructorbyDetailId (int id);
	
}
