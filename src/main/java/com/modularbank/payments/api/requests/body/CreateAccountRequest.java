package com.modularbank.payments.api.requests.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Source;

public class CreateAccountRequest {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String accountName;
	private String accountTypeCode;
	private String currencyCode;
	private String customerGroupCode;
	private String personId;
	private String personName;
	private String priceListTypeCode;
	private String residencyCountryCode;
	private Source sourceDetails = new Source();

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCustomerGroupCode() {
		return customerGroupCode;
	}

	public void setCustomerGroupCode(String customerGroupCode) {
		this.customerGroupCode = customerGroupCode;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPriceListTypeCode() {
		return priceListTypeCode;
	}

	public void setPriceListTypeCode(String priceListTypeCode) {
		this.priceListTypeCode = priceListTypeCode;
	}

	public String getResidencyCountryCode() {
		return residencyCountryCode;
	}

	public void setResidencyCountryCode(String residencyCountryCode) {
		this.residencyCountryCode = residencyCountryCode;
	}

	public Source getSourceDetails() {
		return sourceDetails;
	}

	public void setSourceDetails(Source sourceDetails) {
		this.sourceDetails = sourceDetails;
	}

}
