package com.learning.EduQuest.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.EduQuest.Entity.Instructor;

@Repository
public interface InstructorRepositroy extends JpaRepository<Instructor, Integer> {

	//native query means - database table names and columns are directly used (it is specific to different db)
	
	//JPQL query means - object-oriented query language.Uses entity names and field names from the Java domain model
	//Abstracts the underlying database; queries are based on the entity model.
	
//Native Query Example
	@Query(value ="select i.id,i.email,i.first_name,i.last_name,i.instructor_detail_id,d.hobby,d.youtube_channel from Instructor i left join Instructor_Detail d on  i.instructor_detail_id = d.id where d.id = ?1",nativeQuery = true)
	public Instructor findInstructorByInstructorDetailID (int Id);

//	JPQL Query Example
//	@Query("SELECT i FROM Instructor i LEFT JOIN i.instructorDet d WHERE d.id = ?1")
//	public Instructor findInstructorByInstructorDetailID (int Id);
	
	
	
	//for Deletion of instructor detail only first of all we have to find the instructor and unmap the instructor detail and then delete
	@Modifying  //@Modifying is used to perform update or delete otherwise will get this error -  No results were returned by the query.
	@Query(value = "Delete from instructor_detail id01 where id01.id=?1",nativeQuery = true)
	 void deleteInstructorDetailById(int Id);
}
