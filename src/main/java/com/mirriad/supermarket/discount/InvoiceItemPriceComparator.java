package com.mirriad.supermarket.discount;

import java.util.Comparator;

import com.mirriad.supermarket.models.InvoiceItem;

/**
 * @author duygu
 *
 */
public class InvoiceItemPriceComparator implements Comparator<InvoiceItem> {

	@Override
	public int compare(InvoiceItem i1, InvoiceItem i2) {
		return i1.getItem().getPrice().getValue().compareTo(i2.getItem().getPrice().getValue());
	}

}
