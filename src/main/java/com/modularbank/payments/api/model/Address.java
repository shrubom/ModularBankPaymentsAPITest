package com.modularbank.payments.api.model;

public class Address {
	private String addressTypeCode;
	private String street1;
	private String street2;
	private String cityCounty;
	private String stateRegion;
	private String zip;
	private String countryCode;
	private String moveInDate;

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCityCounty() {
		return cityCounty;
	}

	public void setCityCounty(String cityCounty) {
		this.cityCounty = cityCounty;
	}

	public String getStateRegion() {
		return stateRegion;
	}

	public void setStateRegion(String stateRegion) {
		this.stateRegion = stateRegion;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(String moveInDate) {
		this.moveInDate = moveInDate;
	}

}
