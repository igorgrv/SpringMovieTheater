package com.movieTheater.dao;

import org.springframework.stereotype.Repository;

import com.movieTheater.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 
 * @author igorg
 *
 */
@Repository
public class RoomDao {

    @PersistenceContext
    private EntityManager manager;

    public Room findOne(Integer id) {

        return manager.find(Room.class, id);
    }

    public void save(Room room) {
        manager.merge(room);
    }

    public List<Room> findAll() {
        return manager.createQuery("select r from Room r", Room.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
