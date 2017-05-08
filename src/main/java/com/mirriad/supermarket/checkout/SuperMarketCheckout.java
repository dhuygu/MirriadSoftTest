package com.mirriad.supermarket.checkout;

import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.ShoppingCart;
import com.mirriad.supermarket.payment.PaymentStrategy;

/**
 * @author duygu
 *
 */
public interface SuperMarketCheckout {
	
	public Invoice checkout(ShoppingCart cart);
	public void pay(Invoice invoice, PaymentStrategy paymentStrategy);

}
