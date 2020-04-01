package com.movieTheater.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author igorg
 *
 */
@Entity
public class Buy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	List<Ticket> tickets = new ArrayList<>();

	/**
	 * @deprecated
	 */
	public Buy() {}
	
	// -----------------------------------------------------------------
	// Constructor
	public Buy(List<Ticket> tickets) {
		tickets.forEach(this.tickets::add);
	}
	
	// -----------------------------------------------------------------
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
