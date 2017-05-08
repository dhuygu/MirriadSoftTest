package com.mirriad.supermarket.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * @author duygu
 *
 */
public class ItemTest {

	@Test
	public void test_equals() {
		Price price1 = new Price(new BigDecimal(10));
		Item bread = new Item("1", price1, "bread");
		
		Price price2 = new Price(new BigDecimal(20));
		Item apple = new Item("1", price2, "apple");

		assertThat(apple,  equalTo(bread));
	}
	
	@Test
	public void test_equals2() {
		Price price1 = new Price(new BigDecimal(10));
		Item bread = new Item("1", price1, "bread");
		
		Price price2 = new Price(new BigDecimal(20));
		Item apple = new Item("3", price2, "apple");

		assertFalse(apple.equals(bread));
	}

}
