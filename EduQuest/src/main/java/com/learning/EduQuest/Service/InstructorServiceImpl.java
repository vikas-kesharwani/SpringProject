package com.learning.EduQuest.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.EduQuest.DAO.InstructorRepositroy;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.Entity.Instructor;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

	public InstructorRepositroy instRepo;
	
	@Autowired
	public InstructorServiceImpl( InstructorRepositroy instRepo) {
		this.instRepo = instRepo;
	}
	
	@Override
	public String save(Instructor instructor) {
		// TODO Auto-generated method stub
		Instructor inst = instRepo.save(instructor);
		return "Saved Successfully";
	}

	//for fetching instructor without instructor details
	@Override
	public InstructorModel findInstructorByID(int Id) throws Exception {
		// TODO Auto-generated method stub
		Instructor instructor=instRepo.findById(Id).orElseThrow();
		if(instructor!= null) {
			return instructor.modelMappingwithoutInstDetail();
		}
		
		return null;
	}
	
	//this method is using exactly same Repository method internally but here we are explicitly mapping the instructor detail
	@Override
	public InstructorModel findInstructorwithInstructorDet(int Id) throws Exception {
		// TODO Auto-generated method stub
		Instructor instructor=instRepo.findById(Id).orElseThrow();
		InstructorModel model = new InstructorModel();
		model = instructor.modelMappingwithInstDetail();
		if(instructor!= null) {
			return model;
		}
		
		return null;
	}

	@Override
	public InstructorModel findInstructorbyDetailId(int id) {
		Instructor instructor=instRepo.findInstructorByInstructorDetailID(id);
		InstructorModel model = new InstructorModel();
		model = instructor.modelMappingwithoutInstDetail();
		return model;
	}
	

}
