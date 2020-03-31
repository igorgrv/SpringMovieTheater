package com.movieTheater.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author igorg
 */
@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private BigDecimal price = BigDecimal.ZERO;;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Seat> seat = new HashSet<>();

	/**
	 * @deprecated hibernate only
	 */
	public Room() {

	}

	// Constructor
	// -----------------------------------------------------------------
	public Room(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	// -----------------------------------------------------------------
	// Methods
	public void add(Seat seat) {
		this.seat.add(seat);
	}

	public Map<String, List<Seat>> getMapaDeLugares() {
		if (!this.seat.isEmpty()) {
			return this.seat.stream().collect(Collectors.groupingBy(Seat::getRow, Collectors.toList()));
		}
		return Collections.emptyMap();
	}

	public Integer lugar(String fileira, Integer posicao) {
		Optional<Seat> optional = this.seat.stream()
				.filter((x) -> fileira.equals(x.getRow()) && posicao.equals(x.getLine())).findFirst();
		return optional.get().getId();
	}

	// -----------------------------------------------------------------
	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Seat> getSeat() {
		return seat;
	}

	public void setSeat(Set<Seat> seat) {
		this.seat = seat;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
