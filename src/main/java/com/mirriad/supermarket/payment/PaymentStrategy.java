package com.mirriad.supermarket.payment;

import java.math.BigDecimal;

public interface PaymentStrategy {
	
	public boolean pay(BigDecimal amount);

}
