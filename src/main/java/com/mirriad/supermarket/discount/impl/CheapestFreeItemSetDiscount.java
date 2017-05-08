package com.mirriad.supermarket.discount.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import com.mirriad.supermarket.discount.InvoiceItemPriceComparator;
import com.mirriad.supermarket.discount.ItemSetBasedDiscount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.InvoiceItem;
import com.mirriad.supermarket.models.Item;

/**
 * @author duygu
 *
 */
public class CheapestFreeItemSetDiscount extends ItemSetBasedDiscount {

	private InvoiceItem cheapestItem;

	public CheapestFreeItemSetDiscount(Set<Item> itemSet) {
		super(itemSet);
	}

	public void applyDiscount(Invoice invoice) {

		super.findMatchingItemsToDecideIfDiscountIsApplicable(invoice);

		// means discount is applicable now
		if (validInvoiceItems != null && itemSet.size() == validInvoiceItems.size()) {

			super.calculateHowManyTimesDiscountIsGoingToBeApplied();
			
			Comparator<InvoiceItem> cmp = new InvoiceItemPriceComparator();

			cheapestItem = Collections.min(validInvoiceItems, cmp);

			super.decreaseDiscountedNumberOfItems();

			super.addDiscountAmountToInvoice(calculateAmountOfDiscount(), invoice);
		}
	}


	@Override
	protected BigDecimal calculateAmountOfDiscount() {
		return cheapestItem.getItem().getPrice().getValue().multiply(new BigDecimal(howManyTimesDiscountIsGoingToBeApplied));
	}

}
