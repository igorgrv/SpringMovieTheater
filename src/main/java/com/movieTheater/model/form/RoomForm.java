package com.movieTheater.model.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.movieTheater.model.Seat;
import com.movieTheater.model.Room;

/**
 * 
 * @author igorg
 *
 */
public class RoomForm {
	private Integer roomId;
	@NotBlank
	private String name;
	private List<Seat> seats = new ArrayList<>();

	// -----------------------------------------------------------------
	// Constructors
	public RoomForm() {
	}

	public RoomForm(Room room) {
		this.roomId = room.getId();
		this.name = room.getName();
		this.seats = new ArrayList<>(room.getSeat());
	}

	// -----------------------------------------------------------------
	// Methods
	public Room toSala() {
		Room room = new Room(this.name);
		room.setId(this.roomId);
		room.setSeat(new HashSet<>(this.seats));
		return room;
	}

	// -----------------------------------------------------------------
	// Getters and Setters
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer salaId) {
		this.roomId = salaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
