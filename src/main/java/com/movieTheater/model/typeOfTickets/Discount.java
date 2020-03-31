package com.movieTheater.model.typeOfTickets;

import java.math.BigDecimal;

public interface Discount {

	BigDecimal applyDiscountOn(BigDecimal originalPrice);
	String getDescription();
}
