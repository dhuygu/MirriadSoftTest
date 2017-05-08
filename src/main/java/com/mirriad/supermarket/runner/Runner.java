package com.mirriad.supermarket.runner;

import java.math.BigDecimal;

import com.mirriad.supermarket.checkout.MirriadSuperMarketCheckout;
import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.discount.impl.PayLessThanBulkItemDiscount;
import com.mirriad.supermarket.models.Item;
import com.mirriad.supermarket.models.Price;
import com.mirriad.supermarket.models.ShoppingCart;

public class Runner {

	public static void main(String[] args) {
		// products, discount rules and checkout of market ready
		MirriadSuperMarketCheckout mirriadMarket = new MirriadSuperMarketCheckout();

		Price price1 = new Price(new BigDecimal(10));
		Item bread = new Item("1", price1, "bread");

		Price price2 = new Price(new BigDecimal(20));
		Item tea = new Item("2", price2, "tea");

		Discount discountRule1 = new PayLessThanBulkItemDiscount(3, 2, tea);
		mirriadMarket.addDiscount(discountRule1);

		// shopping done
		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.add(bread);

		shoppingCart.add(tea);
		shoppingCart.add(tea);
		shoppingCart.add(tea);

		// checkout
		mirriadMarket.checkout(shoppingCart);

	}

}
