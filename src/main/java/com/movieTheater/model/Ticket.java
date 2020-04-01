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
	
	private BigDecimal price = BigDecimal.ZERO;
	
	@Enumerated(EnumType.STRING)
	private TypeOfTickets typeOfTickets;
	
	/**
	 * @deprecated
	 */
	public Ticket() {}

	public Ticket(Session session, Seat seat, TypeOfTickets typeOfTickets) {
		this.session = session;
		this.seat = seat;
		this.typeOfTickets = typeOfTickets;
		this.price = typeOfTickets.applyDiscount(session.getPrice());
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

	public void setSession(Session sessao) {
		this.session = sessao;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void setPrice(BigDecimal preco) {
		this.price = preco;
	}

	public TypeOfTickets getTypeOfTickets() {
		return typeOfTickets;
	}

	public void setTypeOfTickets(TypeOfTickets tipoDeIngresso) {
		this.typeOfTickets = tipoDeIngresso;
	}
	
}
