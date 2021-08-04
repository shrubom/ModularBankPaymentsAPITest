package com.modularbank.payments.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Source {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String sourceName;
	private String sourceRef;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

}
