package com.learning.EduQuest.Service;

import java.util.List;

import com.learning.EduQuest.DTO.CourseModel;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.DTO.ReviewModel;
import com.learning.EduQuest.Entity.Course;
import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.Review;

public interface InstructorService {

	String save(Instructor instructor);
	
	InstructorModel findInstructorByID(int Id) throws Exception;

	InstructorModel findInstructorwithInstructorDet(int Id) throws Exception;
	
	InstructorModel findInstructorbyDetailId (int id);
	
	String deleteInstructor (int ID);
	
	String deleteInstructorDetailByID(int Id);
	
	InstructorModel findInstructorWithCourses (int Id);
	
	 List<CourseModel> findCoursesforInst(int Id);

	String deleteCourse(int id);
	
	String addCourseForInstructor(int Id,Course course);
	
	String updateCourse(int courseId,CourseModel model);
	
	String updateInstructor(int Id,InstructorModel model);
	
	void addReview (int Id, ReviewModel model);
	
	String updateReview(int Id, ReviewModel model);
	
	String deleteReview (int Id);
	
	List<Review> getReviewsForCourse(int Id);
	
}