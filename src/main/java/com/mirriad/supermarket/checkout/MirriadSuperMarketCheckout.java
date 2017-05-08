package com.mirriad.supermarket.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.Item;
import com.mirriad.supermarket.models.ShoppingCart;
import com.mirriad.supermarket.payment.PaymentStrategy;

/**
 * @author duygu
 *
 */
public class MirriadSuperMarketCheckout implements SuperMarketCheckout {

	private static final Logger logger = LogManager.getLogger(MirriadSuperMarketCheckout.class.getName());

	// discounts that is valid during the checkout process if there is a
	// predefined discount rule for the items in the basket
	private List<Discount> discounts;

	public MirriadSuperMarketCheckout() {
		discounts = new ArrayList<>();
	}

	public void addDiscount(Discount discount) {
		discounts.add(discount);
	}

	public Invoice checkout(ShoppingCart cart) {
		Invoice invoice = new Invoice();

		for (Item item : cart.getItems()) {
			invoice.addItemOrUpdateQuantity(item);
		}

		PriceCalculator calculator = new PriceCalculator();

		BigDecimal vatPercentage = new BigDecimal(20);

		calculator.calculate(invoice, discounts, vatPercentage);
		
		//it is written to console via logger to show the results
		logger.info(invoice.toString());

		return invoice;
	}

	public void pay(Invoice invoice, PaymentStrategy paymentStrategy) {
		// TODO Auto-generated method stub

	}

}
