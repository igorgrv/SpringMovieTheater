package com.movieTheater.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Lugar;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class SeatDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Lugar lugar) {
        manager.persist(lugar);
    }
    
    public Lugar findOne(Integer id) {
    	return manager.find(Lugar.class, id);
    }
    
    public Seat findOne(Integer id) {
    	return manager.find(Seat.class, id);
    }
}
