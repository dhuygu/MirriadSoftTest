package com.mirriad.supermarket.discount.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mirriad.supermarket.checkout.MirriadSuperMarketCheckout;
import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.Item;
import com.mirriad.supermarket.models.Price;
import com.mirriad.supermarket.models.ShoppingCart;

/**
 * @author duygu
 *
 */
public class CheapestFreeItemSetDiscountTest {

	private ShoppingCart shoppingCart;
	private MirriadSuperMarketCheckout mirriadMarket;
	private Item bread;
	private Item tea;
	private Item banana;

	@Before
	public void setUp() {

		// products, discount rules and checkout of market ready
		mirriadMarket = new MirriadSuperMarketCheckout();

		Price price1 = new Price(new BigDecimal(10));
		bread = new Item("1", price1, "bread");

		Price price2 = new Price(new BigDecimal(20));
		tea = new Item("2", price2, "tea");

		Price price3 = new Price(new BigDecimal(5));
		banana = new Item("3", price3, "banana");

		Set<Item> itemSet = new HashSet<Item>();
		itemSet.add(bread);
		itemSet.add(banana);
		itemSet.add(tea);
		Discount discountRule1 = new CheapestFreeItemSetDiscount(itemSet);
		mirriadMarket.addDiscount(discountRule1);

		// shopping done
		shoppingCart = new ShoppingCart();
	}

	@Test
	public void test1() {

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

		assertThat(invoice.getGrandTotal(), equalTo(new BigDecimal(90)));

		assertThat(invoice.getTotalDiscount(), equalTo(new BigDecimal(5)));

		assertThat(invoice.getVatTotal(), equalTo(new BigDecimal(18)));

	}
	
	@Test
	public void test2() {
		Discount discountRule2 = new PayLessThanBulkItemDiscount(3, 1, tea);
		mirriadMarket.addDiscount(discountRule2);

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

		assertThat(invoice.getGrandTotal(), equalTo(new BigDecimal(90)));

		assertThat(invoice.getTotalDiscount(), equalTo(new BigDecimal(5)));

		assertThat(invoice.getVatTotal(), equalTo(new BigDecimal(18)));

	}
	
	@Test
	public void test3() {
		
		Discount discountRule2 = new PayLessThanBulkItemDiscount(2, 1, tea);
		mirriadMarket.addDiscount(discountRule2);

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

		assertThat(invoice.getGrandTotal(), equalTo(new BigDecimal(70)));

		assertThat(invoice.getTotalDiscount(), equalTo(new BigDecimal(25)));

		assertThat(invoice.getVatTotal(), equalTo(new BigDecimal(14)));

	}
}
