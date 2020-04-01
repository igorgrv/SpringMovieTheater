package com.movieTheater.model.typeOfTickets;

import java.math.BigDecimal;

/**
 * 
 * @author igorg
 *
 */
public interface Discount {

	BigDecimal applyDiscountOn(BigDecimal originalPrice);
	String getDescription();
}
