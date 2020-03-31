package com.movieTheater.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrinho {

	private List<Ingresso> tickets = new ArrayList<>();
	
	public void add(Ingresso ticket) {
		tickets.add(ticket);
	}
	
	public boolean isSelecionado(Lugar lugar) {
		return tickets.stream().map(Ingresso::getLugar).anyMatch(ticket -> ticket.equals(lugar));
	}
	
	public BigDecimal getTotal() {
		return tickets.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
	
//	public Compra toCompra() {
//		return new Compra(ingressos);
//	}

	public void clear() {
		this.tickets.clear();
	}

}
