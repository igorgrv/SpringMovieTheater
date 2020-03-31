package com.movieTheater.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.movieTheater.model.typeOfTickets.TypeOfTickets;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Session session;

	@ManyToOne
	private Seat seat;

	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private TypeOfTickets typeOfTicket;

	/**
	 * @deprecated
	 */
	public Ticket() {
	}

	public Ticket(Session session, Seat seat, TypeOfTickets typeOfTicket) {
		this.session = session;
		this.seat = seat;
		this.typeOfTicket = typeOfTicket;
		this.price = typeOfTicket.applyDiscount(session.getPrice());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat lugar) {
		this.seat = lugar;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void Price(BigDecimal preco) {
		this.price = preco;
	}

	public TypeOfTickets getTypeOfTicket() {
		return typeOfTicket;
	}

	public void setTypeOfTicket(TypeOfTickets typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}

}
