package com.modularbank.payments.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Balances {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)

	private String balanceId;
	private String accountId;
	private String currencyCode;
	private String defaultCurrencyCode;
	private Double balanceAmount;
	private Double reservedAmount;
	private Double overdraftLimitAmount;
	private Double availableBalanceInDefaultCcy;
	private Double availableBalanceAmount;

	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDefaultCurrencyCode() {
		return defaultCurrencyCode;
	}

	public void setDefaultCurrencyCode(String defaultCurrencyCode) {
		this.defaultCurrencyCode = defaultCurrencyCode;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Double getReservedAmount() {
		return reservedAmount;
	}

	public void setReservedAmount(Double reservedAmount) {
		this.reservedAmount = reservedAmount;
	}

	public Double getOverdraftLimitAmount() {
		return overdraftLimitAmount;
	}

	public void setOverdraftLimitAmount(Double overdraftLimitAmount) {
		this.overdraftLimitAmount = overdraftLimitAmount;
	}

	public Double getAvailableBalanceInDefaultCcy() {
		return availableBalanceInDefaultCcy;
	}

	public void setAvailableBalanceInDefaultCcy(Double availableBalanceInDefaultCcy) {
		this.availableBalanceInDefaultCcy = availableBalanceInDefaultCcy;
	}

	public Double getAvailableBalanceAmount() {
		return availableBalanceAmount;
	}

	public void setAvailableBalanceAmount(Double availableBalanceAmount) {
		this.availableBalanceAmount = availableBalanceAmount;
	}

}
