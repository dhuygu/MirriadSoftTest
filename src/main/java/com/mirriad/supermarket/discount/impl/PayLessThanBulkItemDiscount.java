package com.mirriad.supermarket.discount.impl;

import java.math.BigDecimal;

import com.mirriad.supermarket.discount.ItemBasedDiscount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 *
 */
public class PayLessThanBulkItemDiscount extends ItemBasedDiscount {

	private int numberOfItemsGoingToBePaidForBulkItem;

	public PayLessThanBulkItemDiscount(int minBulkItemLimit, int payForBulkItem, Item item) {
		super(item);
		this.minBulkItemLimitToApplyDiscount = minBulkItemLimit;
		this.numberOfItemsGoingToBePaidForBulkItem = payForBulkItem;
	}

	public void applyDiscount(Invoice invoice) {
		super.applyDiscount(invoice);
	}

	protected BigDecimal calculateAmountOfDiscount() {
		BigDecimal amountOfDiscount = new BigDecimal(
				howManyTimesDiscountIsGoingToBeApplied * (minBulkItemLimitToApplyDiscount - numberOfItemsGoingToBePaidForBulkItem))
						.multiply(invoiceItem.getItem().getPrice().getValue());
		return amountOfDiscount;
	}

}
