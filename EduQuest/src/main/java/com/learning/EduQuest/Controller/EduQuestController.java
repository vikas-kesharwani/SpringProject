package com.learning.EduQuest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.EduQuest.DTO.CourseModel;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.DTO.ReviewModel;
import com.learning.EduQuest.Entity.Course;
import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.InstructorDetail;
import com.learning.EduQuest.Entity.Review;
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
	//fetching instructor and/or instructor details
	
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
	
	/********Deleting Instructor and/or instructorDetail**************/
	
	
	
	@DeleteMapping("/deleteInstructorDetail/{Id}")
	public String deleteInstructorDetailByID(@PathVariable int Id) {
		String ans = instServ.deleteInstructorDetailByID(Id);
		return ans;
	}
	
	/********Course related API's**************/
	
	@GetMapping("/coursesForInst/{Id}")
	public ResponseEntity<InstructorModel> findInstructorWithCourses(@PathVariable int Id) throws Exception{
		InstructorModel inst =  new InstructorModel();
		inst = instServ.findInstructorWithCourses(Id);
		return new ResponseEntity<>(inst,HttpStatus.OK);
	}
	
	@GetMapping("/instructorCourses/{Id}")
	public List<CourseModel> findCoursesforInst(@PathVariable int Id) throws Exception{
		List<CourseModel> courses = instServ.findCoursesforInst(Id);
		return  courses;
	}
	
	//delete instructor but not course
	@DeleteMapping("/deleteInstructor/{Id}")
	public String deleteInstructor(@PathVariable int Id) {
		String ans = instServ.deleteInstructor(Id);
		return ans;
	}

	//delete course but not instructor
	@DeleteMapping("/course/{Id}")
	public String deleteCourse(@PathVariable int Id) {
		String ans = instServ.deleteCourse(Id);
		return ans;
	}
	
	//update/add courses for  existing instructors
	
	@PostMapping("/course/{Id}")
	public String addCourseForInst(@PathVariable int Id, @RequestBody CourseModel model) {
		Course course = model.createEntity();
		String ans = instServ.addCourseForInstructor(Id, course);
		return ans;
	}
	
	@PutMapping("course/{Id}")
	public String updateCourse(@PathVariable int Id, @RequestBody CourseModel model) {
		String ans = instServ.updateCourse(Id,model);
		return ans;
	}

	@PutMapping("instructor/{Id}")
	public String updateInstructor(@PathVariable int Id, @RequestBody InstructorModel model) {
		String ans = instServ.updateInstructor(Id,model);
		return ans;
	}
	
	/********Review related API's**************/
	
	@PostMapping("/review/{Id}")
	public String addReview(@PathVariable int Id, @RequestBody ReviewModel model) {
		instServ.addReview(Id, model);
		return "done!";
	}
	@PutMapping("/review/{Id}")
	public String updateReview(@PathVariable int Id, @RequestBody ReviewModel model) {
		String ans = instServ.updateReview(Id, model);
		return ans;
	}
	@DeleteMapping("/review/{Id}")
	public ResponseEntity<String> deleteReview(@PathVariable int Id) {
		String ans =instServ.deleteReview(Id);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}
	
	@GetMapping("/reviews/{courseId}")
	public List<Review> getReviewsForCourse(@PathVariable int courseId){
		List<Review> reviews = instServ.getReviewsForCourse(courseId);
		return reviews;
	}
}
