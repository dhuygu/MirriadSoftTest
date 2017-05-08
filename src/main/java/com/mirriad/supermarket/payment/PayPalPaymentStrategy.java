package com.mirriad.supermarket.payment;

import java.math.BigDecimal;

public class PayPalPaymentStrategy implements PaymentStrategy {

	public boolean pay(BigDecimal amount) {
		System.out.println("Paypal Payment is done!");
		return true;

	}

}
