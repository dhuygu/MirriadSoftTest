package com.mirriad.supermarket.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * @author duygu
 *
 */
public class InvoiceTest {

	@Test
	public void test_addOrUpdate() {
		Price price1 = new Price(new BigDecimal(10));
		Item item1 = new Item("1", price1, "bread");
	
		Invoice invoice = new Invoice();
		
		invoice.addItemOrUpdateQuantity(item1);
		
		Price price2 = new Price(new BigDecimal(10));
		Item item2 = new Item("2", price2, "bread");
		
		invoice.addItemOrUpdateQuantity(item2);
		
		assertThat(invoice.getInvoiceItemsMap().size(), equalTo(2));
		
		invoice.addItemOrUpdateQuantity(item1);
		
		assertThat(invoice.getInvoiceItem(item1).getQuantity(), equalTo(2));
		
		invoice.addItemOrUpdateQuantity(item2);
		invoice.addItemOrUpdateQuantity(item2);
		invoice.addItemOrUpdateQuantity(item2);
		
		assertThat(invoice.getInvoiceItem(item2).getQuantity(), equalTo(4));
	}

}
