package com.modularbank.payments.rest.assured.config;

import com.modularbank.payments.api.utils.Constants;
import com.modularbank.payments.api.utils.RequestHeaders;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfigurations {

	public RequestSpecification postRequestSpecification() {
		RestAssured.baseURI = Constants.authURL;
		return RestAssured.given().headers(RequestHeaders.getHeaders());

	}
}
