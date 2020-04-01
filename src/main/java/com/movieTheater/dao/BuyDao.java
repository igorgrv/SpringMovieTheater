package com.movieTheater.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Buy;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class BuyDao {

	@PersistenceContext
	private EntityManager mv;
	
	public void save(Buy buy) {
		mv.persist(buy);
	}

	
}
