package com.learning.EduQuest.DAO;

import org.springframework.stereotype.Repository;

import com.learning.EduQuest.Entity.Instructor;

import jakarta.persistence.EntityManager;

@Repository
public class AppDaoImpl implements AppDao{

	
	public EntityManager em;
	
	public AppDaoImpl (EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void save(Instructor instructor) {
		// TODO Auto-generated method stub
		em.persist(instructor);
	}

}
