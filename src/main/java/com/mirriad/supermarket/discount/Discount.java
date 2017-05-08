package com.mirriad.supermarket.discount;

import java.math.BigDecimal;

import com.mirriad.supermarket.models.Invoice;

/**
 * @author duygu
 *
 */
public abstract class Discount implements Discountable {

	protected int howManyTimesDiscountIsGoingToBeApplied;

	protected abstract BigDecimal calculateAmountOfDiscount();

	protected abstract void calculateHowManyTimesDiscountIsGoingToBeApplied();

	protected abstract void decreaseDiscountedNumberOfItems();

	protected void addDiscountAmountToInvoice(BigDecimal amountOfDiscount, Invoice invoice) {
		invoice.addDiscountAmount(amountOfDiscount);
	}

}
