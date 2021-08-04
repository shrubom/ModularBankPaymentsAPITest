package com.modularbank.payments.api.model;

public class IdentificationNumber {
	private String idNumber;
	private String idCountryCode;
	private String vatNumber;
	private String taxNumber;

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdCountryCode() {
		return idCountryCode;
	}

	public void setIdCountryCode(String idCountryCode) {
		this.idCountryCode = idCountryCode;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

}
