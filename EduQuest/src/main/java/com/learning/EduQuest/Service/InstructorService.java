package com.learning.EduQuest.Service;

import java.util.List;

import com.learning.EduQuest.DTO.CourseModel;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.DTO.ReviewModel;
import com.learning.EduQuest.DTO.StudentModel;
import com.learning.EduQuest.Entity.Course;
import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.Review;
import com.learning.EduQuest.Entity.Student;

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
	
	List<Course> findAllCourses();
	
	String updateInstructor(int Id,InstructorModel model);
	
	void addReview (int Id, ReviewModel model);
	
	String updateReview(int Id, ReviewModel model);
	
	String deleteReview (int Id);
	
	List<Review> getReviewsForCourse(int Id);
	
	String addStudent(StudentModel model);
	
	String addStudentToCourse(int courseId, int studentId);
	
	String deleteStudent (int Id);
	
	String UpdateStudent (int Id, StudentModel model);
	
	Student findStudentByID (int Id);
	
	List<CourseModel> findCoursesforStudentID (int Id);
	
	List<StudentModel> findAllStudentsInCourse (int Id);
	
}
