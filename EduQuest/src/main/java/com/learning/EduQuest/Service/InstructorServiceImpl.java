package com.learning.EduQuest.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.EduQuest.DAO.InstructorRepositroy;
import com.learning.EduQuest.DTO.CourseModel;
import com.learning.EduQuest.DTO.InstructorModel;
import com.learning.EduQuest.DTO.ReviewModel;
import com.learning.EduQuest.DTO.StudentModel;
import com.learning.EduQuest.Entity.Course;
import com.learning.EduQuest.Entity.Instructor;
import com.learning.EduQuest.Entity.InstructorDetail;
import com.learning.EduQuest.Entity.Review;
import com.learning.EduQuest.Entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

	public InstructorRepositroy instRepo;
	
	@Autowired
	public EntityManager em;
	
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

	@Override
	public String deleteInstructor(int ID) {
		Instructor inst = new Instructor();
		inst = instRepo.findById(ID).orElseThrow();
		
		//before deleting we need to unmap the foreign key refence in course table to avoid exception
		List<Course> listCourse = inst.getCourses();
		for(Course cr:listCourse) {
			cr.setInstructor(null);
		}
		
		  instRepo.delete(inst);
		return "Deletion successful";
	}

	//for Deletion of instructor detail only first of all we have to find the instructor
	//and unmap the instructor detail and then delete
	
	//using entityManager
	@Override
	public String deleteInstructorDetailByID(int Id) {
		 InstructorDetail tempInstructorDetail = em.find(InstructorDetail.class, Id);
		 tempInstructorDetail.getInstructor().setInstructorDet(null);
		 em.remove(tempInstructorDetail);
		return "Instructor Detail deleted";
	}
	
	//using Native Query
		//this will not work if fetch type is Lazy
//		@Override
//		public String deleteInstructorDetailByID(int Id) {
//			 Instructor inst= instRepo.findInstructorByInstructorDetailID(Id);
//			 InstructorDetail tempInstructorDetail = inst.getInstructorDet(); 
//			 tempInstructorDetail.getInstructor().setInstructorDet(null);
//			 instRepo.deleteInstructorDetailById(tempInstructorDetail.getId());
//			return "Instructor Detail deleted";
//		}

	@Override
	public InstructorModel findInstructorWithCourses(int Id) {
		//Get the instructor with courses using native query.
		Instructor inst = new Instructor();
		inst = instRepo.findById(Id).orElseThrow();
		InstructorModel model = new InstructorModel();
		model = inst.modelMappingwithoutInstDetail();
     
		//make explicit call for courses (it is required because fetch type is lazy)
		List<Course> listCourse = inst.getCourses();
		
		//map all the course entity to the course model one by one
		List<CourseModel> coursemodList = new ArrayList<>();
		model.setCourses(coursemodList);
		for(Course cr:listCourse) {
			coursemodList.add(cr.modelMapping());
		}
		
		return model;
	}
	
	@Override
	public List<CourseModel> findCoursesforInst(int Id) {
		//Get the instructor with courses using native query.
		Instructor inst = new Instructor();
		inst = instRepo.findById(Id).orElseThrow();
     
		//make explicit call for courses (it is required because fetch type is lazy)
		List<Course> listCourse = inst.getCourses();
		
		//map all the course entity to the course model one by one
		List<CourseModel> coursemodList = new ArrayList<>();
		for(Course cr:listCourse) {
			coursemodList.add(cr.modelMapping());
		}
		
		return coursemodList;
	}

	@Override
	public String deleteCourse(int id) {
		Course course = em.find(Course.class, id);
		em.remove(course);
		return "course deleted";
	}

	@Override
	public String addCourseForInstructor(int Id,Course course) {
		Instructor inst = instRepo.findById(Id).orElseThrow();
		inst.addCourse(course);
		instRepo.save(inst);
		return "Course added successfully";
	}

	@Override
	public String updateCourse(int courseId,CourseModel model) {
		Course course = em.find(Course.class, courseId);
		
		course.setTitle(model.getTitle());
		em.merge(course);
		return "Course updated successfully";
	}

	@Override
	public String updateInstructor(int Id,InstructorModel model) {
		Instructor inst = instRepo.findById(Id).orElseThrow();
		
		if(model.getEmail() != null)
			inst.setEmail(model.getEmail());
		if(model.getFirstName() != null)
			inst.setFirstName(model.getFirstName());		
		if(model.getLastName() != null)
			inst.setLastName(model.getLastName());
		
		instRepo.save(inst);
		return "Updated successfully";
	}

	@Override
	public void addReview(int Id, ReviewModel model) {
		Course course =  em.find(Course.class, Id);
		Review review = model.createEntity();
		course.addReview(review);
		em.merge(course);
	}

	@Override
	public String updateReview(int Id, ReviewModel model) {
		Review review = em.find(Review.class, Id);
		review.setComment(model.getComment());
		em.merge(review);
		return "updated!";
	}

	@Override
	public String deleteReview(int Id) {
		Review review = em.find(Review.class, Id);
		em.remove(review);
		return "deleted!";
	}
	
	public List<Review> getReviewsForCourse(int Id){
		Course course = em.find(Course.class, Id);
		List<Review> reviews = course.getReviews();
		return reviews;
	}

	@Override
	public List<CourseModel> findAllCourses() {
		String jpql = "SELECT e FROM Course e";	    
	    // Create a TypedQuery instance
	    TypedQuery<Course> query = em.createQuery(jpql, Course.class);	    
	    // Execute the query and get the result list
	    List<Course> list = query.getResultList();
	    List<CourseModel> modelList = new ArrayList<>();
	    for(Course cr : list) {
	    	modelList.add(cr.modelMapping());
	    }
		return modelList;
	}

	@Override
	public String addStudent(StudentModel model) {
		Student st =  new Student();
		st = model.createEntity();
		em.persist(st);
		return "Signed up successfully!";
	}
	
	@Override
	public String addStudentToCourse(int courseId, int studentId) {
		Course course = em.find(Course.class, courseId);
		Student student = em.find(Student.class, studentId);
		course.addStudent(student);
		return "enrolled successfully!";
	}

	@Override
	public String deleteStudent(int Id) {
		Student st = em.find(Student.class, Id);
		
	//I have observed that if the student entity reference is not present in course reference 
//	because of which we do not have to remove the  references separately as shown below-(It is present in course_student table which is 3rd table other than these 2)		
//		List<Course> courses = st.getCourses();
//		for (Course cr : courses) {
//			cr.getStudents().remove(st);
		
	//Sameway in the deleteCourse method we do not have to remove the references separately. 
//		because the course_id foreign key is present in review table but there is uni directional relation between review and course entity.
		
//		Deletion will only throw foreign key violation if there is bi-directional relation as per my observation
//		}
		em.remove(st);
		return "deleted successfully";
	}

	@Override
	public String UpdateStudent(int Id, StudentModel model) {
		Student st = em.find(Student.class, Id);
		if(model.getEmail() != null)
			st.setEmail(model.getEmail());
		if(model.getFirstName() != null)
			st.setFirstName(model.getFirstName());		
		if(model.getLastName() != null)
			st.setLastName(model.getLastName());
		em.merge(st);
		return "Details updated successfully!";
	}

	@Override
	public Student findStudentByID(int Id) {
		Student st = em.find(Student.class, Id);
		return st;
	}

	@Override
	public List<CourseModel> findCoursesforStudentID(int Id) {
		Student st = em.find(Student.class, Id);
		List<Course> courses = st.getCourses();
		List<CourseModel> list = new ArrayList();
		for(Course cr : courses) {
			list.add(cr.modelMapping());
		}
		return list;
	}

	@Override
	public List<StudentModel> findAllStudentsInCourse(int Id) {
		Course  course = em.find(Course.class, Id);
		List<Student> students = course.getStudents();
		List<StudentModel> list = new ArrayList();
		for(Student st : students) {
			list.add(st.modelMapping());
		}
		return list;
	}
	
	
	

}
