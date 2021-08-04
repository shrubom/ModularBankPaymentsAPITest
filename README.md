# Modular Bank Payments API Test
**Postman Collection is placed in the resource folder** 
## Test Strategy

1. Perform Manual testing using postman 
2. Create maven project and set up **TestNG** automation framework .
3. Define and run test cases .

	
## Test cases

### Testcase 1: Get the JWT authentication

* Validate the status code is **200**
* Save the token to be used in all other APIs.

	> code samples for the methods in BaseClass .

   ```java
   
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
   
   ```

	```java
	
	@Test(groups = "paymentsAPI", priority = 0)
	public void postJWTRequest() throws InterruptedException, JSONException, IOException {
		log.info("Getting the JWT Token");
		tokenVal = jwtTokenExtract().getTokenId();
	}
	```
	
### Testcase 2: Create Customer details

* Implement object.properties file to get the required input for the request body .<br>
* Validate if the status is 200 .
* Fetch the person Id to be used in the CreatAccountAPI .

	> code samples for the methods in BaseClass .
	
	```java
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
	```
	
### Testcase 3: Create Account for the new customer

* Use the PersonId received from the previous post API and provide the appropriate request body .
* Validate that the status is 200 
* Fetch account id for the payments and transactions api

```java
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
```


### Testcase 4: Create Transactions for the newly created account

* Use the accountId from the previous service call and provide appropriate request body .
* Validate if the status is 200 .

```java
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
```
### Testcase 5: Initialize payment for account
* Use the accountId from the previous service call and provide appropriate request body .
* Validate if the status is 200 .
```java
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
```
### Testcase 6: Confirm payment
* Use the accountId from the previous service call and provide appropriate request body .
* Validate if the status is 200 .

```java
	@Test(groups = "paymentsAPI", priority = 5)
	public void confirmPayment() {
		log.info("Confirm a Payment for a new Payment");
		confirmPayment(tokenVal, accountId, paymentId);

	}
```

### Testcase 7: Get the newly created account details

* Get the account details using the account ID .
* Validate if the status is 200 .


```java
@Test(groups = "paymentsAPI", priority = 0)
		public void getAccountDetails() throws JSONException {
			log.info("Printing the newly created account details");
			getAccount(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.personId, PaymentsPostTestCases.accountId);
		}
```

### Testcase 8: Get the account balance
* Get the account balance using the account ID .
* Validate if the status is 200 .

```java
@Test(groups = "paymentsAPI", priority = 1)
		public void getAccountBalances() {
			log.info("Get the Account balances of the newely created account");
			getAccBalance(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId, PaymentsPostTestCases.currencyCode);

		}
```

### Testcase 9: Get account statement history
* Get the account statement using the account ID .
* Validate if the status is 200 .

```java
@Test(groups = "paymentsAPI", priority = 2)
		public void getAccountStatement() {
			log.info("Get the Account balances of any account number");
			getAccStatements(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId);
		}
```
