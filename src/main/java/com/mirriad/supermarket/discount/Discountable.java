package com.mirriad.supermarket.discount;

import com.mirriad.supermarket.models.Invoice;

/**
 * @author duygu
 *
 */
public interface Discountable {
	
	public void applyDiscount(Invoice invoice);


}
