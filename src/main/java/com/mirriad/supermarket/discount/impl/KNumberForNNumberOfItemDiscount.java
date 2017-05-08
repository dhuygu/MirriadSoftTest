package com.mirriad.supermarket.discount.impl;

import java.math.BigDecimal;

import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.InvoiceItem;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 *
 */
public class KNumberForNNumberOfItemDiscount extends Discount {
	private int numberOfRequiredItems;
	private Item requiredItemToApplyDiscount;

	private int numberOfItemTobeDiscounted;
	private Item itemToBeDiscounted;

	private InvoiceItem requiredInvoiceItem;
	private InvoiceItem toBeDiscountedInvoiceItem;

	public KNumberForNNumberOfItemDiscount(int numberOfRequiredItems, Item requiredItemToApplyDiscount, int numberOfItemTobeDiscounted,
			Item itemToBeDiscounted) {
		this.numberOfRequiredItems = numberOfRequiredItems;
		this.requiredItemToApplyDiscount = requiredItemToApplyDiscount;
		this.numberOfItemTobeDiscounted = numberOfItemTobeDiscounted;
		this.itemToBeDiscounted = itemToBeDiscounted;
	}

	@Override
	public void applyDiscount(Invoice invoice) {

		requiredInvoiceItem = invoice.getInvoiceItem(requiredItemToApplyDiscount);
		if (requiredInvoiceItem != null && requiredInvoiceItem.getNumberOfItemsNotCoveredForDiscount() >= numberOfRequiredItems) {
			toBeDiscountedInvoiceItem = invoice.getInvoiceItem(itemToBeDiscounted);
			if (toBeDiscountedInvoiceItem != null
					&& toBeDiscountedInvoiceItem.getNumberOfItemsNotCoveredForDiscount() >= numberOfItemTobeDiscounted) {
				// means discount is applicable
				calculateHowManyTimesDiscountIsGoingToBeApplied();
				calculateAmountOfDiscount();
				
				decreaseDiscountedNumberOfItems();
				super.addDiscountAmountToInvoice(calculateAmountOfDiscount(), invoice);
			}
		}

	}

	@Override
	protected BigDecimal calculateAmountOfDiscount() {
		BigDecimal oneDiscountAmount = itemToBeDiscounted.getPrice().getValue().multiply(new BigDecimal(numberOfItemTobeDiscounted));
		return new BigDecimal(howManyTimesDiscountIsGoingToBeApplied).multiply(oneDiscountAmount);
	}

	@Override
	protected void calculateHowManyTimesDiscountIsGoingToBeApplied() {
		howManyTimesDiscountIsGoingToBeApplied = requiredInvoiceItem.getNumberOfItemsNotCoveredForDiscount() / numberOfRequiredItems;
	}

	@Override
	protected void decreaseDiscountedNumberOfItems() {
		requiredInvoiceItem.decreaseNumberOfNonDiscountedItemNumber(numberOfRequiredItems * howManyTimesDiscountIsGoingToBeApplied);
		toBeDiscountedInvoiceItem
				.decreaseNumberOfNonDiscountedItemNumber(numberOfItemTobeDiscounted * howManyTimesDiscountIsGoingToBeApplied);
	}

}
