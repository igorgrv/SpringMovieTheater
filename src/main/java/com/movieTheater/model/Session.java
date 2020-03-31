package com.movieTheater.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

//	@OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
//	private Set<Ingresso> tickets = new HashSet<>();

	/**
	 * @deprecated hibernat only
	 */
	public Session() {	}

	public Session(Movie filme, Room sala, LocalTime horario) {
		this.movie = filme;
		this.room = sala;
		this.time = horario;
		this.price = sala.getPrice().add(filme.getPrice());
	}

	public Map<String, List<Seat>> getMapaDeLugares() {
		return room.getMapaDeLugares();
	}

//	public boolean isDisponivel(Room room) {
//	return tickets.stream().map(Ingresso::getLugar).noneMatch(l -> l.equals(lugar));
//}

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

}
