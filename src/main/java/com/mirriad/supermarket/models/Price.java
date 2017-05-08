package com.mirriad.supermarket.models;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 * @author duygu
 *
 */
public class Price {

	private BigDecimal value;
	private Currency currency;

	public Price(BigDecimal value) {
		//assumed it is always GBP
		this.currency = Currency.getInstance(Locale.UK);
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
