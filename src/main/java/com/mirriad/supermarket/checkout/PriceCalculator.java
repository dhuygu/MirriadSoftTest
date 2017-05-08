package com.mirriad.supermarket.checkout;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.mirriad.supermarket.discount.Discount;
import com.mirriad.supermarket.models.Invoice;
import com.mirriad.supermarket.models.InvoiceItem;

/**
 * @author duygu
 *
 */
public class PriceCalculator {


	public void calculate(Invoice invoice, List<Discount> discounts, BigDecimal vatPercentage) {

		BigDecimal itemsActualAmountTotal = new BigDecimal(0);

		if (invoice.getInvoiceItemsMap() != null) {

			for (Map.Entry<String, InvoiceItem> entry : invoice.getInvoiceItemsMap().entrySet()) {
				entry.getValue().calculateTotalAmount();
				itemsActualAmountTotal = itemsActualAmountTotal.add(entry.getValue().getTotalAmount());
			}

			invoice.setActualPrice(itemsActualAmountTotal);

			for (Discount discount : discounts) {
				discount.applyDiscount(invoice);
			}

			invoice.setGrandTotal(invoice.getActualPrice().subtract(invoice.getTotalDiscount()));

			invoice.setVatPercentage(vatPercentage);

			invoice.setVatTotal(invoice.getGrandTotal().multiply(invoice.getVatPercentage()).divide(new BigDecimal(100)));

		}
	}

}
