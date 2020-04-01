package com.movieTheater.model.typeOfTickets;

import java.math.BigDecimal;

/**
 * 
 * @author igorg
 *
 */
public enum TypeOfTickets {

	NORMAL(new NullDiscount()),
	STUDENT(new StudentDiscount());
	
	private final Discount discount;
	
	TypeOfTickets(Discount discount){
		this.discount = discount;
	}
	
	public BigDecimal applyDiscount(BigDecimal originalPrice) {
		return discount.applyDiscountOn(originalPrice);
	}
	
	public String getDescription() {
		return discount.getDescription();
	}
}
