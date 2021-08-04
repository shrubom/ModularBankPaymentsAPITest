package com.modularbank.payments.api.utils;

import java.util.Arrays;
import java.util.List;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class RequestHeaders {

	public static Headers getHeaders() {
		Header h1 = new Header(Constants.tenantCode, Constants.tenantCode_Value);
		Header h2 = new Header(Constants.channelCode, Constants.channelCode_Value);
		Header h3 = new Header(Constants.contentType,Constants.contentType_Value);
		List<Header> list = Arrays.asList(h1, h2,h3);
		Headers header = new Headers(list);
		return header;
	}

}
