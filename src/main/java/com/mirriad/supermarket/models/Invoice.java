package com.mirriad.supermarket.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author duygu
 *
 */
public class Invoice {

	private BigDecimal actualPrice;
	private BigDecimal grandTotal;
	private BigDecimal vatTotal;
	private BigDecimal vatPercentage;
	private BigDecimal totalDiscount;
	private Map<String, InvoiceItem> invoiceItemsMap;
	private LocalDateTime processDate;

	public Invoice() {
		this.invoiceItemsMap = new HashMap<String, InvoiceItem>();
		actualPrice = new BigDecimal(0);
		vatTotal = new BigDecimal(0);
		grandTotal = new BigDecimal(0);
		totalDiscount = new BigDecimal(0);
		processDate = LocalDateTime.now();
	}

	public Map<String, InvoiceItem> getInvoiceItemsMap() {
		return invoiceItemsMap;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getVatTotal() {

		return vatTotal;
	}

	public void setVatTotal(BigDecimal vatTotal) {
		this.vatTotal = vatTotal;
	}

	public BigDecimal getVatPercentage() {
		return vatPercentage;
	}

	public void setVatPercentage(BigDecimal vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

	public LocalDateTime getProcessDate() {
		return processDate;
	}

	public InvoiceItem getInvoiceItem(Item item) {
		return invoiceItemsMap.get(item.getId());
	}

	public void addDiscountAmount(BigDecimal amount) {
		totalDiscount = totalDiscount.add(amount);
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void addItemOrUpdateQuantity(Item item) {
		if (invoiceItemsMap.get(item.getId()) != null) {
			invoiceItemsMap.get(item.getId()).increaseQuantity();
		} else {
			InvoiceItem invoiceItem = new InvoiceItem(item);
			invoiceItemsMap.put(item.getId(), invoiceItem);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("\n");
		builder.append("Process Date:");
		builder.append(getProcessDate());
		builder.append("\n");
		builder.append("\n");

		builder.append(invoiceItemsMap.values().toString());
		builder.append("\n");
		builder.append("Actual Total:");
		builder.append(getActualPrice());
		builder.append("\n");

		builder.append("Total Discount Amount:");
		builder.append(getTotalDiscount());
		builder.append("\n");
		
		builder.append("Vat Percentage:");
		builder.append("\t");
		builder.append(getVatPercentage());
		builder.append("\n");

		builder.append("Vat Total:");
		builder.append("\t");
		builder.append(getVatTotal());
		builder.append("\n");

		builder.append("******************************");
		builder.append("\n");

		builder.append("Grand Total:");
		builder.append("\t");
		builder.append(getGrandTotal());

		return builder.toString();
	}
}
