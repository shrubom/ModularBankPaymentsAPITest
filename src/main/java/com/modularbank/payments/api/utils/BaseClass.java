package com.modularbank.payments.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import com.modularbank.payments.api.model.Address;
import com.modularbank.payments.api.model.Counterparty;
import com.modularbank.payments.api.model.Document;
import com.modularbank.payments.api.model.IdentificationNumber;
import com.modularbank.payments.api.model.Money;
import com.modularbank.payments.api.model.Source;
import com.modularbank.payments.api.requests.body.CreateAccountRequest;
import com.modularbank.payments.api.requests.body.CreateCustomerRequest;
import com.modularbank.payments.api.requests.body.CreatePaymentRequest;
import com.modularbank.payments.api.requests.body.CreateTransactionsRequest;
import com.modularbank.payments.api.requests.body.JWTTokenRequest;
import com.modularbank.payments.api.response.body.CreateAccountResponse;
import com.modularbank.payments.api.response.body.CreateCustomerResponse;
import com.modularbank.payments.api.response.body.CreatePaymentResponse;
import com.modularbank.payments.api.response.body.CreateTransactionResponse;
import com.modularbank.payments.api.response.body.JWTTokenResponse;
import com.modularbank.payments.rest.assured.config.RestAssuredConfigurations;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	InputStream input = BaseClass.class.getClassLoader().getResourceAsStream("object.properties");
	Properties prop = new Properties();
	

	private static final Logger log = LogManager.getLogger(BaseClass.class);
	public static String getResponse = null, token = null, data = null;
	public static Response response = null;
	public static RequestSpecification getAccResponse = null;
	int getStatusCode;
	public static Date date = new Date();
	public static long time = date.getTime();
	RequestSpecification postReqSpec = new RestAssuredConfigurations().postRequestSpecification();

	public JWTTokenResponse jwtTokenExtract() throws JSONException, IOException {
		
		prop.load(input);

		RestAssured.baseURI = Constants.authURL;
		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec);
		JWTTokenRequest jtr = new JWTTokenRequest();
		//jtr.setUserName("modular.system");
		jtr.setUserName(prop.getProperty("userName"));
		jtr.setPassword(prop.getProperty("password"));
		response = request.body(jtr).when().post("api/v1/employees/authorise");
		String jsonStringVal = response.asString();
		JSONObject jsonObj = new JSONObject(jsonStringVal);
		String tokenString = jsonObj.getJSONObject("data").getString("token");
		JWTTokenResponse tokenResponse = new JWTTokenResponse();
		tokenResponse.setTokenId(tokenString);
		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		return tokenResponse;

	}

	public HashSet<CreateCustomerResponse> createCustomer(String tokenValue) throws JSONException {

		String personId = null, fullName = null, docId = null, addressId = null;
		String passportIdNumber = null, givenName = null;
		HashSet<CreateCustomerResponse> customerHash = new HashSet();
		RandonIdGenerator identificationString = new RandonIdGenerator();
		RandonIdGenerator docIdString = new RandonIdGenerator();
		RandonIdGenerator alphaStringGen = new RandonIdGenerator();
		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec).header(Constants.authToken, tokenValue);
		CreateCustomerRequest custReq = new CreateCustomerRequest();
		custReq.setGivenName(prop.getProperty("givenName") + alphaStringGen.randomAlphaGen());
		custReq.setSurname(prop.getProperty("surName"));
		custReq.setMiddleName(prop.getProperty("middleName"));
		custReq.setBirthDate("1990-12-01");
		custReq.setPersonTypeCode("P");
		custReq.setSex("F");
		custReq.setEmail("and.mandi@gmail.com");
		custReq.setPhoneNumber("0901820");
		custReq.setPhoneCountryCode("+372");
		custReq.setEducationCode("HIGHER_EDUCATION");
		custReq.setActivityCode("SPECIALIST");
		custReq.setHousingTypeCode("PRIVATE");
		custReq.setBuildingTypeCode("APARTMENT");
		custReq.setBusinessAreaCode("LEGAL");
		custReq.setMaritalStatusCode("MARRIED");
		custReq.setDependantPersons(2);
		custReq.setEmploymentTimeCode("MORE_4_YEAR");
		custReq.setCustomerType("string");
		custReq.setNationality("DE");
		custReq.setPlaceOfBirth("Berlin");
		custReq.setCountryOfBirth("DE");
		custReq.setLanguage("DE");
		custReq.setTaxResidencyCountry("DE");
		custReq.setFixedEmploymentLength(5);
		custReq.setUsResident(true);
		custReq.setPep(true);
		IdentificationNumber idNum = new IdentificationNumber();
		idNum.setIdCountryCode("DE");
		idNum.setIdNumber(identificationString.randomIdGen());
		idNum.setTaxNumber("12345678901");
		idNum.setVatNumber("DE123456789");
		custReq.setIdentificationNumber(idNum);
		Document doc = new Document();
		doc.setDocumentTypeCode("PASSPORT");
		doc.setExpiryDate("2025-01-03");
		doc.setIssuingCountry("DE");
		doc.setNumber(docIdString.randomIdGen());
		custReq.setDocument(doc);
		Address address = new Address();
		address.setAddressTypeCode("R");
		address.setCityCounty("Berlin");
		address.setCountryCode("DE");
		address.setMoveInDate("2018-06-23");
		address.setStateRegion("Berlin");
		address.setStreet1("Fennstrasse 4");
		address.setStreet2("string");
		address.setZip("13347");
		custReq.getAddresses().add(address);

		response = request.body(custReq).when().post(Constants.person_Api_URL);
		String strResponse = response.asString();
		CreateCustomerResponse customerObj = new CreateCustomerResponse();
		JSONObject jsonCustObj = new JSONObject(strResponse);
		personId = jsonCustObj.getJSONObject("data").getString("personId");
		givenName = jsonCustObj.getJSONObject("data").getString("givenName");
		fullName = jsonCustObj.getJSONObject("data").getString("fullName");
		passportIdNumber = jsonCustObj.getJSONObject("data").getString("idNumber");

		// getting the address id number
		JSONObject dataObject = jsonCustObj.getJSONObject("data");
		JSONArray addressesArr = dataObject.getJSONArray("addresses");
		for (int i = 0; i < addressesArr.length(); i++) {
			addressId = addressesArr.getJSONObject(i).getString("addressId");
			
		}

		// getting the document id number
		JSONArray docArr = dataObject.getJSONArray("documents");
		for (int i = 0; i < docArr.length(); i++) {
			docId = docArr.getJSONObject(i).getString("docuId");
			
		}

		// Setting the values of the parameters to the response class object
		customerObj.setPersonId(personId);
		customerObj.setFullName(fullName);
		customerObj.setIdentificationNumber(passportIdNumber);
		customerObj.setDocuId(docId);
		customerObj.setAddressId(addressId);
		customerObj.setGivenName(givenName);

		customerHash.add(customerObj);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		return customerHash;

	}

	// This method creates the account for a new User
	public HashSet<CreateAccountResponse> createAccount(String tokenValue, String personId, String personName)
			throws JSONException {

		UUID uuid = UUID.randomUUID();
		String accountId = null, personIdAcc = null;
		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec).header(Constants.authToken, tokenValue);
		CreateAccountRequest customerAccountReq = new CreateAccountRequest();
		HashSet<CreateAccountResponse> accountHash = new HashSet();

		customerAccountReq.setAccountName("Demo account");
		customerAccountReq.setAccountTypeCode("CURRENCY");
		customerAccountReq.setCurrencyCode("EUR");
		customerAccountReq.setCustomerGroupCode("GROUP_A");
		customerAccountReq.setPersonId(personId);
		customerAccountReq.setPersonName(personName);
		customerAccountReq.setPriceListTypeCode("STANDARD");
		customerAccountReq.setResidencyCountryCode("FI");
		Source src = new Source();
		src.setSourceName("TEST");
		src.setSourceRef(uuid.toString());
		customerAccountReq.setSourceDetails(src);

		response = request.body(customerAccountReq).when()
				.post(Constants.account_Api_URL + "persons/" + personId + "/accounts");
		String strResponse = response.asString();
		CreateAccountResponse accountObj = new CreateAccountResponse();
		JSONObject jsonCustObj = new JSONObject(strResponse);
		accountId = jsonCustObj.getJSONObject("data").getString("accountId");
		personId = jsonCustObj.getJSONObject("data").getString("personId");
		personName = jsonCustObj.getJSONObject("data").getString("personName");

		accountObj.setAccountId(accountId);
		accountObj.setPersonId(personId);
		accountObj.setPersonName(personName);

		accountHash.add(accountObj);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		return accountHash;

	}

	// getting the account details
	public void getAccount(String tokenVal, String personId, String accountId) {

		RequestSpecification request = RestAssured.given();
		response = request.spec(postReqSpec).header(Constants.authToken, tokenVal).when()
				.get(Constants.account_Api_URL + "accounts/" + accountId);
		String strResponse = response.asString();
		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

	}

	public HashSet<CreateTransactionResponse> createTransactions(String tokenVal, String personId, String accountId)
			throws JSONException {

		String accountTransactionId = null, transactionTypeCode = null, directionCode = null, currencyCode = null;
		Double transAmount = 0.00;
		HashSet<CreateTransactionResponse> transactionHash = new HashSet();

		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec).header(Constants.authToken, tokenVal);
		CreateTransactionsRequest transReq = new CreateTransactionsRequest();

		transReq.setDetails("Card Top Up");
		transReq.setEffectiveDate("2021-06-08");
		transReq.setTransactionTypeCode("CARD_TOPUP");
		Money mon = new Money();
		mon.setAmount(238.00);
		mon.setCurrencyCode("EUR");
		Source srcTransact = new Source();
		srcTransact.setSourceName("CARD_TOPUP");
		srcTransact.setSourceRef("ID-" + time);
		transReq.setMoney(mon);
		transReq.setSourceTransact(srcTransact);

		response = request.body(transReq).when().post(Constants.transaction_Api_URL + accountId + "/transactions");

		String strResponse = response.asString();
		CreateTransactionResponse transObj = new CreateTransactionResponse();
		JSONObject jsonTransObj = new JSONObject(strResponse);
		JSONArray dataArr = jsonTransObj.getJSONArray("data");
		for (int i = 0; i < dataArr.length(); i++) {
			accountTransactionId = dataArr.getJSONObject(i).getString("accountTransactionId");
			transactionTypeCode = dataArr.getJSONObject(i).getString("transactionTypeCode");
			directionCode = dataArr.getJSONObject(i).getString("directionCode");
			transAmount = Double.parseDouble(dataArr.getJSONObject(i).getString("amount"));
			currencyCode = dataArr.getJSONObject(i).getString("currencyCode");
		}

		transObj.setAccountTransactionId(accountTransactionId);
		transObj.setTransactionTypeCode(transactionTypeCode);
		transObj.setDirectionCode(directionCode);
		transObj.setAmount(transAmount);
		transObj.setCurrencyCode(currencyCode);

		transactionHash.add(transObj);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		return transactionHash;

	}

	// Get the account balance
	public void getAccBalance(String tokenVal, String accountId, String currencyCode) {
		RestAssured.baseURI = Constants.authURL;
		RequestSpecification request = RestAssured.given();
		request.queryParam(Constants.currency_Code, currencyCode);
		request.header(Constants.tenantCode, Constants.tenantCode_Value)
				.header(Constants.channelCode, Constants.channelCode_Value).header(Constants.authToken, tokenVal)
				.header(Constants.contentType, Constants.contentType_Value);
		request.when().get(Constants.account_Api_URL + "accounts/" + accountId + "/available-balance");
		String strResponse = response.asString();
		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

	}

	// Initializing a payment
	public HashSet<CreatePaymentResponse> createPayment(String tokenVal, String accountId) throws JSONException {
		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec).header(Constants.authToken, tokenVal);
		String paymentId = null;
		HashSet<CreatePaymentResponse> paymentHash = new HashSet();
		CreatePaymentRequest payReq = new CreatePaymentRequest();
		Counterparty payCount = new Counterparty();
		payCount.setName("Adam Ficher");
		payCount.setCounterpartyTypeCode("IBAN");
		payCount.setValue("EE459999000000010140");

		payReq.setCounterparty(payCount);
		payReq.setDetails("Details");
		payReq.setDirectionCode("OUT");
		payReq.setEffectiveDate("2020-06-08");
		payReq.setEndToEndId("PROVIDED");
		payReq.setPaymentTransferTypeCode("REGULAR");
		payReq.setPaymentTypeCode("ACC2SEPA");

		Money pay = new Money();
		pay.setAmount(23.54);
		pay.setCurrencyCode("EUR");
		payReq.setMoney(pay);

		Source paySrc = new Source();
		paySrc.setSourceName("PAYMENT");
		paySrc.setSourceRef("ID" + time);
		payReq.setSource(paySrc);

		response = request.body(payReq).when().post(Constants.payment_Api_URL + accountId + "/payments/initialise");
		String strResponse = response.asString();
		CreatePaymentResponse payRes = new CreatePaymentResponse();
		JSONObject jsonPayObj = new JSONObject(strResponse);
		accountId = jsonPayObj.getJSONObject("data").getString("accountId");
		paymentId = jsonPayObj.getJSONObject("data").getString("paymentId");

		payRes.setAccountId(accountId);
		payRes.setPaymentId(paymentId);

		paymentHash.add(payRes);
		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}
		return paymentHash;
	}

	// Confirm Payment
	public void confirmPayment(String tokenVal, String accountId, String paymentId) {
		RequestSpecification request = RestAssured.given();
		request.spec(postReqSpec).header(Constants.authToken, tokenVal);
		CreatePaymentRequest payReq = new CreatePaymentRequest();
		Counterparty payCount = new Counterparty();
		payCount.setName("Adam Ficher");
		payCount.setCounterpartyTypeCode("IBAN");
		payCount.setValue("EE459999000000010140");

		payReq.setCounterparty(payCount);
		payReq.setDetails("Details");
		payReq.setDirectionCode("OUT");
		payReq.setEffectiveDate("2020-06-08");
		payReq.setEndToEndId("PROVIDED");
		payReq.setPaymentTransferTypeCode("REGULAR");
		payReq.setPaymentTypeCode("ACC2SEPA");

		Money pay = new Money();
		pay.setAmount(23.54);
		pay.setCurrencyCode("EUR");
		payReq.setMoney(pay);

		Source paySrc = new Source();
		paySrc.setSourceName("PAYMENT");
		paySrc.setSourceRef("ID" + time);
		payReq.setSource(paySrc);

		response = request.body(payReq).when()
				.post(Constants.payment_Api_URL + accountId + "/payments/" + paymentId + "/confirm");
		String strResponse = response.asString();

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

	}

	// Get the account statements
	public void getAccStatements(String tokenVal, String accountId) {
		
		RestAssured.baseURI = Constants.transaction_Api_URL;
		RequestSpecification request = RestAssured.given();
		request.header(Constants.tenantCode, Constants.tenantCode_Value)
				.header(Constants.channelCode, Constants.channelCode_Value).header(Constants.authToken, tokenVal)
				.header(Constants.contentType, Constants.contentType_Value);
		request.get(Constants.transaction_Api_URL + accountId + "/transactions/search");	
		String strResponse = response.asString();
		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

	}

}
