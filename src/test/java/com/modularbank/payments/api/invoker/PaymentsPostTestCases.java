package com.modularbank.payments.api.invoker;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.modularbank.payments.api.response.body.CreateAccountResponse;
import com.modularbank.payments.api.response.body.CreateCustomerResponse;
import com.modularbank.payments.api.response.body.CreatePaymentResponse;
import com.modularbank.payments.api.response.body.CreateTransactionResponse;
import com.modularbank.payments.api.utils.BaseClass;
import com.modularbank.payments.rest.assured.config.RestAssuredConfigurations;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PaymentsPostTestCases extends BaseClass {

	private static final Logger log = LogManager.getLogger(PaymentsPostTestCases.class);
	public static String tokenVal = null, accountId = null, currencyCode = null, personId = null;
	public String personName = null;
	public String personIdAcc = null;
	public String personNameAcc = null;
	public String paymentId = null;
	public String getResponse = null;
	public String token = null;
	public String data = null;
	public String insideData = null;
	public String accountTransactionId = null;
	public String transactionTypeCode = null;
	public String directionCode = null;
	public String firstName = null;
	public String surName = null;
	public String idNumber = null;
	public static HashSet<CreateCustomerResponse> custRes = new HashSet<CreateCustomerResponse>();
	public static HashSet<CreateAccountResponse> accRes = new HashSet<CreateAccountResponse>();
	public static HashSet<CreateTransactionResponse> transRes = new HashSet<CreateTransactionResponse>();
	public static HashSet<CreatePaymentResponse> payRes = new HashSet<CreatePaymentResponse>();
	public static Response response = null;
	public static Double transAmount = 0.00;
	RequestSpecification postReqSpec = new RestAssuredConfigurations().postRequestSpecification();

	// get the JWT Token for further use
	@Test(groups = "paymentsAPI", priority = 0)
	public void postJWTRequest() throws InterruptedException, JSONException, IOException {
		log.info("Getting the JWT Token");
		tokenVal = jwtTokenExtract().getTokenId();
	}

	// Create Customer API
	@Test(groups = "paymentsAPI", priority = 1)
	public void postCreateCustomer() throws JSONException {
		log.info("Creating a new Customer detail");
		custRes = createCustomer(tokenVal);
		Iterator<CreateCustomerResponse> custIterator = custRes.iterator();
		while (custIterator.hasNext()) {
			CreateCustomerResponse custItem = custIterator.next();
			personId = custItem.getPersonId();
			this.personName = custItem.getGivenName();
		}
	}

	// Create Account API
	@Test(groups = "paymentsAPI", priority = 2)
	public void postCreateAccount() throws JSONException {
		log.info("Opening an account for the newly created customer");
		accRes = createAccount(tokenVal, personId, personName);
		Iterator<CreateAccountResponse> accIterator = accRes.iterator();
		while (accIterator.hasNext()) {
			CreateAccountResponse accItem = accIterator.next();
			accountId = accItem.getAccountId();
			this.personIdAcc = accItem.getPersonId();
			this.personNameAcc = accItem.getPersonName();
		}

	}

	// Create Transactions for the newly created account
	@Test(groups = "paymentsAPI", priority = 3)
	public void postTransactions() throws JSONException {
		log.info("Create transaction for the account");
		transRes = createTransactions(tokenVal, personId, accountId);
		Iterator<CreateTransactionResponse> iterator = transRes.iterator();
		while (iterator.hasNext()) {
			CreateTransactionResponse transItem = iterator.next();
			this.accountTransactionId = transItem.getAccountTransactionId();
			this.transactionTypeCode = transItem.getTransactionTypeCode();
			currencyCode = transItem.getCurrencyCode();

		}
	}

	// Initialize Payment for an account
	@Test(groups = "paymentsAPI", priority = 4)
	public void postPayment() throws JSONException {
		log.info("Initialize Payment for an account");
		payRes = createPayment(tokenVal, accountId);
		Iterator<CreatePaymentResponse> iterator = payRes.iterator();
		while (iterator.hasNext()) {
			CreatePaymentResponse transItem = iterator.next();
			this.paymentId = transItem.getPaymentId();
		}

	}

	// Confirm Payment for newly initialized Payment
	@Test(groups = "paymentsAPI", priority = 5)
	public void confirmPayment() {
		log.info("Confirm a Payment for a new Payment");
		confirmPayment(tokenVal, accountId, paymentId);

	}

}
