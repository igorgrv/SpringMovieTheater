package com.movieTheater.model.typeOfTickets;

import java.math.BigDecimal;

/**
 * 
 * @author igorg
 *
 */
public class NullDiscount implements Discount{

	@Override
	public BigDecimal applyDiscountOn(BigDecimal originalPrice) {
		return originalPrice;
	}

	@Override
	public String getDescription() {
		return "Normal";
	}

}
