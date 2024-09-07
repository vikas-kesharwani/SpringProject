package com.learning.EduQuest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.EduQuest.DAO.AppDao;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.InstructorDetail;
import com.learning.EduQuest.Service.InstructorService;

@RestController
@RequestMapping("/EduQuest")
public class EduQuestController {
	
	public InstructorService instServ;
	
	@Autowired
	public EduQuestController(InstructorService instServ) {
		this.instServ =  instServ;
	}
	
	//Get Started
	@GetMapping("/hi")
	public String displayHi() {
		return "Hey! welcome" ;
	}  
	

	@PostMapping("/saveInstructor")
    public String saveInstructor(@RequestBody InstructorModel instMod){
		String response = "";
		Instructor instructor = new Instructor();
		instructor = instMod.createEntityMapping();
		response = instServ.save(instructor);
		return response;
	}
	
	
	/********Learning Fetch Type, JPQL, Native Query**************/
	//keep fetchtype Lazy for instructor detail and then test this method
	@GetMapping("/instructor/{Id}")
	public ResponseEntity<InstructorModel> findInstructor(@PathVariable int Id) throws Exception{
		InstructorModel inst =  new InstructorModel();
		inst = instServ.findInstructorByID(Id);
		return new ResponseEntity<>(inst,HttpStatus.OK);
	}
	
	//keep fetchtype Lazy for instructor detail and then test this method
	@GetMapping("/instructorwithDet/{Id}")
	public ResponseEntity<InstructorModel> findInstructorwithDetails(@PathVariable int Id) throws Exception{
		InstructorModel inst =  new InstructorModel();
		inst = instServ.findInstructorwithInstructorDet(Id);
		return new ResponseEntity<>(inst,HttpStatus.OK);
	}
	
	@GetMapping("/instructorbyDetId/{Id}")
	public ResponseEntity<InstructorModel> findInstructorbyDetailId(@PathVariable int Id) throws Exception{
		InstructorModel inst =  new InstructorModel();
		inst = instServ.findInstructorbyDetailId(Id);
		return new ResponseEntity<>(inst,HttpStatus.OK);
	}
	
	/********Learning Fetch Type, JPQL, Native Query**************/
	
	
	
	
}
