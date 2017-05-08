package com.mirriad.supermarket.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duygu
 *
 */
public class ShoppingCart {

	private List<Item> items;

	public ShoppingCart() {
		items = new ArrayList<Item>();
	}

	public void add(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void remove(Item item) {

	}

	public void remove(List<Item> item) {

	}

}
