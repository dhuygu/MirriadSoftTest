package com.mirriad.supermarket.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class InvoiceItemTest {

	private InvoiceItem invoiceItem1;

	@Before
	public void setUp() throws Exception {
		Price price1 = new Price(new BigDecimal(10));
		Item item1 = new Item("1", price1, "bread");
		invoiceItem1 = new InvoiceItem(item1);
	}

	@Test
	public void test_quantity() {

		invoiceItem1.increaseQuantity();

		invoiceItem1.increaseQuantity();

		assertThat(invoiceItem1.getQuantity(), equalTo(3));

		invoiceItem1.decreaseNumberOfNonDiscountedItemNumber(1);

		assertThat(invoiceItem1.getNumberOfItemsNotCoveredForDiscount(), equalTo(2));
	}

	@Test
	public void test_decreaseNumberOfNonDiscountedItemNumber() {
		
		invoiceItem1.increaseQuantity();
		
		invoiceItem1.increaseQuantity();
		
		invoiceItem1.calculateTotalAmount();
		
		assertThat(3, equalTo(invoiceItem1.getNumberOfItemsNotCoveredForDiscount()));
		
		invoiceItem1.decreaseNumberOfNonDiscountedItemNumber(1);
		
		assertThat(new BigDecimal(30), equalTo(invoiceItem1.getTotalAmount()));
		
		assertThat(2, equalTo(invoiceItem1.getNumberOfItemsNotCoveredForDiscount()));

	}

}
