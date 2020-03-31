package com.movieTheater.model;

import java.time.YearMonth;

public class Card {

	
	private String cardNumber;
	private Integer cvv;
	private YearMonth expires;
	
	public boolean isAvailable() {
		return expires.isAfter(YearMonth.now());
	}
	
	
	public String getNumero() {
		return cardNumber;
	}
	public void setNumero(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public YearMonth getVencimento() {
		return expires;
	}
	public void setVencimento(YearMonth expires) {
		this.expires = expires;
	}
	
	
}
