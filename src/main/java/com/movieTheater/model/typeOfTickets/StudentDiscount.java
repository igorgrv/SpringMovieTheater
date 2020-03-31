package com.movieTheater.model.typeOfTickets;

import java.math.BigDecimal;

public class StudentDiscount implements Discount{

	@Override
	public BigDecimal applyDiscountOn(BigDecimal originalPrice) {
		return originalPrice.divide(new BigDecimal("2"));
	}

	@Override
	public String getDescription() {
		return "Student Discount";
	}

}
