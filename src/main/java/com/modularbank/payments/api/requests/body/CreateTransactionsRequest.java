package com.modularbank.payments.api.requests.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Money;
import com.modularbank.payments.api.model.Source;

public class CreateTransactionsRequest {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String details;
	private String effectiveDate;
	private String transactionTypeCode;

	Money money = new Money();
	Source sourceTransact = new Source();

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Source getSourceTransact() {
		return sourceTransact;
	}

	public void setSourceTransact(Source sourceTransact) {
		this.sourceTransact = sourceTransact;
	}

}
