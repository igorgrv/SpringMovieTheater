package com.movieTheater.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Ticket;
import com.movieTheater.model.Seat;
import com.movieTheater.model.Session;
import com.movieTheater.model.typeOfTickets.TypeOfTickets;

public class ShopCartForm {

	private List<Ticket> tickets = new ArrayList<>();

	public List<Ticket> toIngressos(SessionDao sessaoDao, SeatDao lugarDao){
		return this.tickets.stream().map(ticket -> {
			Session sessao = sessaoDao.findOne(ticket.getSession().getId());
			Seat seat = lugarDao.findOne(ticket.getSeat().getId());
			TypeOfTickets tipoDeIngresso = ticket.getTypeOfTickets();
			return new Ticket(sessao, seat, tipoDeIngresso);
		}).collect(Collectors.toList());
	}
	
	public List<Ticket> getIngressos() {
		return tickets;
	}

	public void setIngressos(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
