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
		custReq.setBirthDate(prop.getProperty("dateOfBirth"));
		
		custReq.setPersonTypeCode(prop.getProperty("personTypeCode"));
		custReq.setSex(prop.getProperty("sex"));
		custReq.setEmail(prop.getProperty("email"));
		custReq.setPhoneNumber(prop.getProperty("phoneNumber"));
		custReq.setPhoneCountryCode(prop.getProperty("phoneCountryCode"));
		custReq.setEducationCode(prop.getProperty("educationCode"));
		custReq.setActivityCode(prop.getProperty("activityCode"));
		custReq.setHousingTypeCode(prop.getProperty("housingTypeCode"));
		custReq.setBuildingTypeCode(prop.getProperty("buildingTypeCode"));
		custReq.setBusinessAreaCode(prop.getProperty("businessAreaCode"));
		custReq.setMaritalStatusCode(prop.getProperty("maritalStatusCode"));
		custReq.setDependantPersons(2);
		custReq.setEmploymentTimeCode(prop.getProperty("employmentTimeCode"));
		custReq.setCustomerType(prop.getProperty("customerType"));
		custReq.setNationality(prop.getProperty("nationality"));
		custReq.setPlaceOfBirth(prop.getProperty("placeOfBirth"));
		custReq.setCountryOfBirth(prop.getProperty("countryOfBirth"));
		custReq.setLanguage(prop.getProperty("language"));
		custReq.setTaxResidencyCountry(prop.getProperty("taxResidencyCountry"));
		custReq.setFixedEmploymentLength(5);
		custReq.setUsResident(true);
		custReq.setPep(true);
		IdentificationNumber idNum = new IdentificationNumber();
		idNum.setIdCountryCode(prop.getProperty("countryCode"));
		idNum.setIdNumber(identificationString.randomIdGen());
		idNum.setTaxNumber(prop.getProperty("taxNumber"));
		idNum.setVatNumber(prop.getProperty("vatNumber"));
		custReq.setIdentificationNumber(idNum);
		Document doc = new Document();
		doc.setDocumentTypeCode(prop.getProperty("documentTypeCode"));
		doc.setExpiryDate(prop.getProperty("expiryDate"));
		doc.setIssuingCountry(prop.getProperty("issuingCountry"));
		doc.setNumber(docIdString.randomIdGen());
		custReq.setDocument(doc);
		Address address = new Address();
		address.setAddressTypeCode(prop.getProperty("addressTypeCode"));
		address.setCityCounty(prop.getProperty("cityCounty"));
		address.setCountryCode(prop.getProperty("countryCode"));
		address.setMoveInDate(prop.getProperty("moveInDate"));
		address.setStateRegion(prop.getProperty("stateRegion"));
		address.setStreet1(prop.getProperty("street1"));
		address.setStreet2(prop.getProperty("street2"));
		address.setZip(prop.getProperty("zip"));
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

		customerAccountReq.setAccountName(prop.getProperty("accountName"));
		customerAccountReq.setAccountTypeCode(prop.getProperty("accountTypeCode"));
		customerAccountReq.setCurrencyCode(prop.getProperty("currencyCode"));
		customerAccountReq.setCustomerGroupCode(prop.getProperty("customerGroupCode"));
		customerAccountReq.setPersonId(personId);
		customerAccountReq.setPersonName(personName);
		customerAccountReq.setPriceListTypeCode(prop.getProperty("priceListTypeCode"));
		customerAccountReq.setResidencyCountryCode(prop.getProperty("residencyCountryCode"));
		Source src = new Source();
		src.setSourceName(prop.getProperty("sourceName"));
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

		transReq.setDetails(prop.getProperty("details"));
		transReq.setEffectiveDate(prop.getProperty("effectiveDate"));
		transReq.setTransactionTypeCode(prop.getProperty("transactionTypeCode"));
		Money mon = new Money();
		mon.setAmount(238.00);
		mon.setCurrencyCode(prop.getProperty("currencyCode"));
		Source srcTransact = new Source();
		srcTransact.setSourceName(prop.getProperty("sourceName"));
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
		payCount.setName(prop.getProperty("name"));
		payCount.setCounterpartyTypeCode(prop.getProperty("counterpartyTypeCode"));
		payCount.setValue(prop.getProperty("value"));

		payReq.setCounterparty(payCount);
		payReq.setDetails(prop.getProperty("paymentDetails"));
		payReq.setDirectionCode(prop.getProperty("directionCode"));
		payReq.setEffectiveDate(prop.getProperty("effectiveDate"));
		payReq.setEndToEndId(prop.getProperty("endToEndId"));
		payReq.setPaymentTransferTypeCode(prop.getProperty("paymentTransferTypeCode"));
		payReq.setPaymentTypeCode(prop.getProperty("paymentTypeCode"));

		Money pay = new Money();
		pay.setAmount(23.54);
		pay.setCurrencyCode(prop.getProperty("currencyCode"));
		payReq.setMoney(pay);

		Source paySrc = new Source();
		paySrc.setSourceName(prop.getProperty("sourceName"));
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
		payCount.setName(prop.getProperty("name"));
		payCount.setCounterpartyTypeCode(prop.getProperty("counterpartyTypeCode"));
		payCount.setValue(prop.getProperty("value"));

		payReq.setCounterparty(payCount);
		payReq.setDetails(prop.getProperty("paymentDetails"));
		payReq.setDirectionCode(prop.getProperty("directionCode"));
		payReq.setEffectiveDate(prop.getProperty("effectiveDate"));
		payReq.setEndToEndId(prop.getProperty("endToEndId"));
		payReq.setPaymentTransferTypeCode(prop.getProperty("paymentTransferTypeCode"));
		payReq.setPaymentTypeCode(prop.getProperty("paymentTypeCode"));

		Money pay = new Money();
		pay.setAmount(23.54);
		pay.setCurrencyCode(prop.getProperty("currencyCode"));
		payReq.setMoney(pay);

		Source paySrc = new Source();
		paySrc.setSourceName(prop.getProperty("paymentSourceName"));
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
