package com.mirriad.supermarket.models;

import java.math.BigDecimal;

/**
 * @author duygu
 *
 */
public class InvoiceItem {

	private int quantity;
	private Item item;
	private BigDecimal totalAmount;
	private int numberOfItemsNotCoveredForDiscount;

	public InvoiceItem(Item item) {
		this.item = item;
		quantity = 1;
		numberOfItemsNotCoveredForDiscount = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}


	public int getNumberOfItemsNotCoveredForDiscount() {
		return numberOfItemsNotCoveredForDiscount;
	}

	public void decreaseNumberOfNonDiscountedItemNumber(int numberOfItemCoveredForDiscount) {
		this.numberOfItemsNotCoveredForDiscount = this.numberOfItemsNotCoveredForDiscount - numberOfItemCoveredForDiscount;
	}
	
	public void increaseQuantity(){
		quantity = quantity  + 1;
		numberOfItemsNotCoveredForDiscount = quantity;
	}
	

	public void calculateTotalAmount() {
		this.totalAmount = item.getPrice().getValue().multiply(new BigDecimal(quantity));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("item:");
		builder.append(item.getName());
		builder.append("\t");
		builder.append("quantity:");
		builder.append(getQuantity());
		builder.append("\t");
		builder.append("Amount:");
		builder.append(getTotalAmount());
		builder.append("\n");
		return builder.toString();
	}

}
