package com.modularbank.payments.api.requests.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "username", "password" })
public class JWTTokenRequest {
	
	@JsonProperty("username")
	private String userName;

	@JsonProperty("password")
	private String password;

	public JWTTokenRequest() {
	}

	public JWTTokenRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JWTTokenRequest [userName=" + userName + ", password=" + password + "]";
	}
	
	

}
