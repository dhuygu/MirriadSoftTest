package com.mirriad.supermarket.models;

/**
 * @author duygu
 *
 */
public class Item {

	private final String id;
	// name is only for readility of the items while test.
	private final String name;
	private Price price;

	public Item(String id, Price price, String name) {
		this.id = id;
		this.price = price;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Item)) {
			return false;
		}

		Item item = (Item) obj;

		return item.id.equals(id);
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + price.hashCode();
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:");
		builder.append(id);
		builder.append("price:");
		builder.append(price.getValue());
		builder.append("currency");
		builder.append(price.getCurrency());
		return super.toString();
	}
}
