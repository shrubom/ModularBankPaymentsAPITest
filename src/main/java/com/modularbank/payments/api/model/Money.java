package com.modularbank.payments.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Money {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	 
	private Double amount;
	private String currencyCode;
	
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

}
