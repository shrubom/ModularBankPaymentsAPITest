package com.modularbank.payments.api.response.body;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTTokenResponse {
	@JsonProperty("token")
	private String tokenId;

	public JWTTokenResponse() {
	}

	public JWTTokenResponse(String tokenId) {
		super();
		this.tokenId = tokenId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Override
	public String toString() {
		return "JWTTokenResponse [tokenId=" + tokenId + "]";
	}

}
