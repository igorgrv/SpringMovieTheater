package com.movieTheater.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Seat;
import com.movieTheater.model.Session;
import com.movieTheater.model.Ticket;
import com.movieTheater.model.typeOfTickets.TypeOfTickets;



@Repository
public class ShopCartForm {
	private List<Ticket> tickets = new ArrayList<>();

	public List<Ticket> toTickets(SessionDao sessionDao, SeatDao seatDao){
		return this.tickets.stream().map(tickets -> {
			Session session = sessionDao.findOne(tickets.getSession().getId());
			Seat seat = seatDao.findOne(tickets.getSeat().getId());
			TypeOfTickets typeOfTickets = tickets.getTypeOfTicket();
			return new Ticket(session, seat, typeOfTickets);
		}).collect(Collectors.toList());
	}
		
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
