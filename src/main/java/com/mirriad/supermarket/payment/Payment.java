package com.mirriad.supermarket.payment;

import com.mirriad.supermarket.models.Invoice;

public class Payment {
	
	private PaymentStrategy paymentStrategy;
	
	
	public Payment(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}
	
	public void pay(Invoice invoice){
		paymentStrategy.pay(invoice.getGrandTotal());
	}

}
