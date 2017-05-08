package com.mirriad.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.InvoiceItem;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 * 
 * 
 */
public abstract class ItemSetBasedDiscount extends Discount {

	protected Set<Item> itemSet;
	protected List<InvoiceItem> validInvoiceItems;

	public ItemSetBasedDiscount(Set<Item> itemSet) {
		this.itemSet = itemSet;
	}

	protected abstract BigDecimal calculateAmountOfDiscount();

	@Override
	protected void calculateHowManyTimesDiscountIsGoingToBeApplied() {
		howManyTimesDiscountIsGoingToBeApplied = validInvoiceItems.stream().mapToInt(InvoiceItem::getNumberOfItemsNotCoveredForDiscount)
				.min().getAsInt();
	}

	protected void findMatchingItemsToDecideIfDiscountIsApplicable(Invoice invoice) {
		validInvoiceItems = new ArrayList<InvoiceItem>();
		
		for (Item entry : itemSet) {
			InvoiceItem invoiceItem = invoice.getInvoiceItem(entry);
			if (invoiceItem != null && invoiceItem.getNumberOfItemsNotCoveredForDiscount() > 0) {
				validInvoiceItems.add(invoiceItem);
			} else {
				break;
			}
		}
	}

	@Override
	protected void decreaseDiscountedNumberOfItems() {
		for (InvoiceItem invoiceItem : validInvoiceItems) {
			invoiceItem.decreaseNumberOfNonDiscountedItemNumber(howManyTimesDiscountIsGoingToBeApplied);
		}
	}
}
