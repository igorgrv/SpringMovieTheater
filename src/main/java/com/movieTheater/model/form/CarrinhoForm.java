package com.movieTheater.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Ingresso;
import com.movieTheater.model.Lugar;
import com.movieTheater.model.Session;
import com.movieTheater.model.typeOfTickets.TypeOfTickets;

public class CarrinhoForm {

	private List<Ingresso> ingressos = new ArrayList<>();

	public List<Ingresso> toIngressos(SessionDao sessaoDao, SeatDao lugarDao){
		return this.ingressos.stream().map(ingresso -> {
			Session sessao = sessaoDao.findOne(ingresso.getSession().getId());
			Lugar lugar = lugarDao.findOne(ingresso.getLugar().getId());
			TypeOfTickets tipoDeIngresso = ingresso.getTipoDeIngresso();
			return new Ingresso(sessao, lugar, tipoDeIngresso);
		}).collect(Collectors.toList());
	}
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
}
