package com.movieTheater.model.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.movieTheater.model.Lugar;
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
	@NotBlank
	private BigDecimal price = BigDecimal.ZERO;

	private List<Lugar> lugars = new ArrayList<>();

	// -----------------------------------------------------------------
	// Constructors
	public RoomForm() {
	}

	public RoomForm(Room room) {
		this.roomId = room.getId();
		this.name = room.getName();
		this.lugars = new ArrayList<>(room.getSeat());
	}

	// -----------------------------------------------------------------
	// Methods
	public Room toSala() {
		Room room = new Room(this.name, this.price);
		room.setId(this.roomId);
		room.setSeat(new HashSet<>(this.lugars));
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

	public List<Lugar> getSeats() {
		return lugars;
	}

	public void setSeats(List<Lugar> lugars) {
		this.lugars = lugars;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
