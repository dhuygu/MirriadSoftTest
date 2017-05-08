package com.mirriad.supermarket.payment;

import java.math.BigDecimal;

public class CreditCardPaymentStrategy implements PaymentStrategy{

	public boolean pay(BigDecimal amount) {
		System.out.println("Credit card payment is done!");
		return true;
	}

}
