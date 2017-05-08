package com.mirriad.supermarket.discount.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.mirriad.supermarket.checkout.MirriadSuperMarketCheckout;
import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.Item;
import com.mirriad.supermarket.models.Price;
import com.mirriad.supermarket.models.ShoppingCart;

public class PayLessThanBulkItemDiscountTest {

	@Test
	public void test() {

		// products, discount rules and checkout of market ready
		MirriadSuperMarketCheckout mirriadMarket = new MirriadSuperMarketCheckout();

		Price price1 = new Price(new BigDecimal(10));
		Item bread = new Item("1", price1, "bread");

		Price price2 = new Price(new BigDecimal(20));
		Item tea = new Item("2", price2, "tea");

		Price price3 = new Price(new BigDecimal(5));
		Item banana = new Item("3", price3, "banana");

		Discount discountRule1 = new PayLessThanBulkItemDiscount(3, 2, tea);
		mirriadMarket.addDiscount(discountRule1);

		Discount discountRule2 = new PayLessThanBulkItemDiscount(4, 1, banana);
		mirriadMarket.addDiscount(discountRule2);

		// shopping done
		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.add(bread);

		shoppingCart.add(tea);
		shoppingCart.add(tea);
		shoppingCart.add(tea);

		shoppingCart.add(banana);
		shoppingCart.add(banana);
		shoppingCart.add(banana);
		shoppingCart.add(banana);
		shoppingCart.add(banana);

		// checkout
		Invoice invoice = mirriadMarket.checkout(shoppingCart);

		assertThat(invoice.getActualPrice(), equalTo(new BigDecimal(95)));

		assertThat(invoice.getGrandTotal(), equalTo(new BigDecimal(60)));

		assertThat(invoice.getTotalDiscount(), equalTo(new BigDecimal(35)));

		assertThat(invoice.getVatTotal(), equalTo(new BigDecimal(12)));


	}

}
