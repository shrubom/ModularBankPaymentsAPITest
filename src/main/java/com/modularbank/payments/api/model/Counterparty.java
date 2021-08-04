package com.modularbank.payments.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Counterparty {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String counterpartyTypeCode;
	private String name;
	private String value;

	public String getCounterpartyTypeCode() {
		return counterpartyTypeCode;
	}

	public void setCounterpartyTypeCode(String counterpartyTypeCode) {
		this.counterpartyTypeCode = counterpartyTypeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
