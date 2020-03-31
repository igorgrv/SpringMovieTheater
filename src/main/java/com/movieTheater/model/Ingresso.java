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
public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Session sessao;
	
	@ManyToOne
	private Lugar lugar;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private TypeOfTickets tipoDeIngresso;
	
	/**
	 * @deprecated
	 */
	public Ingresso() {}

	public Ingresso(Session sessao, Lugar lugar, TypeOfTickets tipoDeIngresso) {
		this.sessao = sessao;
		this.lugar = lugar;
		this.tipoDeIngresso = tipoDeIngresso;
		this.preco = tipoDeIngresso.applyDiscount(sessao.getPrice());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Session getSession() {
		return sessao;
	}

	public void setSession(Session sessao) {
		this.sessao = sessao;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public TypeOfTickets getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TypeOfTickets tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}
	
}
