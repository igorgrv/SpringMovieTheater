package com.movieTheater.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author igorg
 *
 */
@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	private Movie movie;
	@ManyToOne
	private Room room;
	private BigDecimal price;
	private LocalTime time;

	@OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
	private Set<Ticket> tickets = new HashSet<>();

	/**
	 * @deprecated hibernat only
	 */
	public Session() {
	}

	// -----------------------------------------------------------------
	// Constructor
	public Session(Movie movie, Room room, LocalTime horario) {
		this.movie = movie;
		this.room = room;
		this.time = horario;
		this.price = room.getPrice().add(movie.getPrice());
	}

	// -----------------------------------------------------------------
	// Methods
	public Map<String, List<Seat>> getMapaDeSeats() {
		return room.getMapaDeSeats();
	}

	public boolean isAvailable(Seat seat) {
		return tickets.stream().map(Ticket::getSeat).noneMatch(l -> l.equals(seat));
	}

	// -----------------------------------------------------------------
	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Set<Ticket> getIngressos() {
		return tickets;
	}

	public void setIngressos(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

}
