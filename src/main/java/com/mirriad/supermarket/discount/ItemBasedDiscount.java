package com.mirriad.supermarket.discount;

import java.math.BigDecimal;

import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.InvoiceItem;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 *
 */
public abstract class ItemBasedDiscount extends Discount {

	protected Item item;
	protected int minBulkItemLimitToApplyDiscount;
	protected InvoiceItem invoiceItem;

	public ItemBasedDiscount(Item item) {
		this.item = item;
	}

	public void applyDiscount(Invoice invoice) {
		setInvoiceItem(invoice);
		if (invoiceItem != null) {
			calculateHowManyTimesDiscountIsGoingToBeApplied();
			BigDecimal amountOfDiscount = calculateAmountOfDiscount();
			addDiscountAmountToInvoice(amountOfDiscount, invoice);
			decreaseDiscountedNumberOfItems();
		}

	}

	@Override
	protected void calculateHowManyTimesDiscountIsGoingToBeApplied() {
		howManyTimesDiscountIsGoingToBeApplied = invoiceItem.getNumberOfItemsNotCoveredForDiscount() / minBulkItemLimitToApplyDiscount;
	}

	public int getNumberOfDiscountedItem() {
		return howManyTimesDiscountIsGoingToBeApplied * minBulkItemLimitToApplyDiscount;
	}

	public void setInvoiceItem(Invoice invoice) {
		invoiceItem = invoice.getInvoiceItem(item);
	}

	protected void decreaseDiscountedNumberOfItems() {
		invoiceItem.decreaseNumberOfNonDiscountedItemNumber(getNumberOfDiscountedItem());
	}

}
