package com.movieTheater.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Seat;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class SeatDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Seat seat) {
        manager.persist(seat);
    }
    
    public Seat findOne(Integer id) {
    	return manager.find(Seat.class, id);
    }
}
