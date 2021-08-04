package com.modularbank.payments.api.requests.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Counterparty;
import com.modularbank.payments.api.model.Money;
import com.modularbank.payments.api.model.Source;

public class CreatePaymentRequest {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	Counterparty counterparty = new Counterparty();
	private String details;
	private String directionCode;
	private String effectiveDate;
	private String endToEndId;	
	Money money = new Money();
	private String paymentTransferTypeCode;
	private String paymentTypeCode;
	Source source = new Source();
	

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getPaymentTransferTypeCode() {
		return paymentTransferTypeCode;
	}

	public void setPaymentTransferTypeCode(String paymentTransferTypeCode) {
		this.paymentTransferTypeCode = paymentTransferTypeCode;
	}

	public Counterparty getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	

}
