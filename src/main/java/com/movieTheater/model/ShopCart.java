package com.movieTheater.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class ShopCart {

	private List<Ticket> tickets = new ArrayList<>();
	
	public void add(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public boolean isAvailable(Seat seat) {
		return tickets.stream().map(Ticket::getSeat).anyMatch(ticket -> ticket.equals(seat));
	}
	
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