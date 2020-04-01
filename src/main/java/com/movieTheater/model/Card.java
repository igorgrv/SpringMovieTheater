package com.movieTheater.model;

import java.time.YearMonth;

public class Card {

	private String cardNumber;
	private Integer cvv;
	private YearMonth expires;

	public boolean isValidated() {
		return expires.isAfter(YearMonth.now());
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public YearMonth getExpires() {
		return expires;
	}

	public void setExpires(YearMonth expires) {
		this.expires = expires;
	}

}
