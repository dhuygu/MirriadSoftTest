package com.mirriad.supermarket.discount.impl;

import java.math.BigDecimal;

import com.mirriad.supermarket.discount.ItemBasedDiscount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 *
 */
public class SpecialPriceForBulkItemDiscount extends ItemBasedDiscount {

	private BigDecimal specialPrice;

	public SpecialPriceForBulkItemDiscount(int minBulkItemLimitToApplyDiscount, BigDecimal specialPrice, Item item) {
		super(item);
		this.minBulkItemLimitToApplyDiscount = minBulkItemLimitToApplyDiscount;
		this.specialPrice = specialPrice;
	}

	public void applyDiscount(Invoice invoice) {
		super.applyDiscount(invoice);
	}

	@Override
	protected BigDecimal calculateAmountOfDiscount() {
		BigDecimal discountedPriceForBulks = new BigDecimal(howManyTimesDiscountIsGoingToBeApplied).multiply(specialPrice);
		int nonDiscountedNumberOfItems = invoiceItem.getNumberOfItemsNotCoveredForDiscount() - getNumberOfDiscountedItem();
		BigDecimal priceForNonDiscounted = new BigDecimal(nonDiscountedNumberOfItems).multiply(item.getPrice().getValue());
		BigDecimal amountOfDiscount = invoiceItem.getTotalAmount().subtract(discountedPriceForBulks.add(priceForNonDiscounted));
		return amountOfDiscount;
	}

}
