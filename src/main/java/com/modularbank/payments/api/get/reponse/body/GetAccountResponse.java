package com.modularbank.payments.api.get.reponse.body;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Balances;

public class GetAccountResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)

	private String accountId;
	private String personId;
	private String accountTypeCode;
	private String activationDate;
	private String accountName;
	private String personName;
	private String statusCode;
	private String iban;
	private String bic;
	private String defaultCurrencyCode;
	private String tenantCode;
	private String residencyCode;
	private String customerGroupCode;
	// Objects
	private List<Balances> balance = new ArrayList<Balances>();

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getDefaultCurrencyCode() {
		return defaultCurrencyCode;
	}

	public void setDefaultCurrencyCode(String defaultCurrencyCode) {
		this.defaultCurrencyCode = defaultCurrencyCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getResidencyCode() {
		return residencyCode;
	}

	public void setResidencyCode(String residencyCode) {
		this.residencyCode = residencyCode;
	}

	public String getCustomerGroupCode() {
		return customerGroupCode;
	}

	public void setCustomerGroupCode(String customerGroupCode) {
		this.customerGroupCode = customerGroupCode;
	}

	public List<Balances> getBalance() {
		return balance;
	}

	public void setBalance(List<Balances> balance) {
		this.balance = balance;
	}

}
