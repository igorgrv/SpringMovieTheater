package com.movieTheater.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Movie;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class SessionDao {
	@PersistenceContext
	private EntityManager mg;

	public void save(Session session) {
		mg.persist(session);
	}

	public List<Session> listDeSessionsPorRoom(Room room) {
		TypedQuery<Session> query = mg.createQuery("SELECT s FROM Session s WHERE s.room = :room", Session.class);
		query.setParameter("room", room);
		return query.getResultList();
	}

	public Session findOne(Integer id) {
		return mg.find(Session.class, id);
	}

	public List<Session> listDeSessionsPorMovie(Movie movie) {
		TypedQuery<Session> query = mg.createQuery("SELECT s FROM Session s WHERE s.movie = :movie", Session.class);
		query.setParameter("movie", movie);
		return query.getResultList();
	}
}
