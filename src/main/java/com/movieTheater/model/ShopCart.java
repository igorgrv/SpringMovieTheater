package com.movieTheater.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 
 * @author igorg
 *
 */
@Component
@SessionScope
public class ShopCart {

	private List<Ticket> tickets = new ArrayList<>();

	// -----------------------------------------------------------------
	// Methods
	public void add(Ticket ticket) {
		tickets.add(ticket);
	}

	public boolean isSelected(Seat seat) {
		return tickets.stream().map(Ticket::getSeat).anyMatch(ticket -> ticket.equals(seat));
	}

	// -----------------------------------------------------------------
	// Getters and Setters
	public BigDecimal getTotal() {
		return tickets.stream().map(Ticket::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public Buy toCompra() {
		return new Buy(tickets);
	}

	public void clear() {
		this.tickets.clear();
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
