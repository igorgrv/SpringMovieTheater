package com.movieTheater.dao;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class MovieDao {

    @PersistenceContext
    private EntityManager manager;


    public Movie findOne(Integer id) {
        return manager.find(Movie.class, id);
    }

    public void save(Movie movie) {
        manager.persist(movie);
    }

    public List<Movie> findAll() {
        return manager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
