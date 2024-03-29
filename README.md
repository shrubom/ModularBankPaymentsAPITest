# Modular Bank Payments API Test
**Postman Collection is placed in the resource folder** 

## Automation Testing Strategy:

1. Identify the feasibility of automation.
2. Selecting the right tool for automation.
3. Test Management Strategy
4. Test Environment Management
5. Automation Test Script Development and Execution
6. Test analysis and generation of Test Reports


## API Test Strategy

1. Perform Manual testing using postman .
2. Create maven project and use **TestNG** automation framework .
3. Define and run test cases .

	
## Test Cases

### TestCase 1: Get the JWT authentication

* Validate the status code is **200**
* Save the token to be used in all other APIs.

	> code sample for the methods in BaseClass .

   ```java
   
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
   
   ```
    > code samples for the Tests using TestNG .
    
	```java
	
	@Test(groups = "paymentsAPI", priority = 0)
	public void postJWTRequest() throws InterruptedException, JSONException, IOException {
		log.info("Getting the JWT Token");
		tokenVal = jwtTokenExtract().getTokenId();
	}
	```
	
### TestCase 2: Create Customer details

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
	
### TestCase 3: Create Account for the new customer

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


### TestCase 4: Create Transactions for the newly created account

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
### TestCase 5: Initialize payment for account
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
### TestCase 6: Confirm payment
* Use the accountId from the previous service call and provide appropriate request body .
* Validate if the status is 200 .

```java
	@Test(groups = "paymentsAPI", priority = 5)
	public void confirmPayment() {
		log.info("Confirm a Payment for a new Payment");
		confirmPayment(tokenVal, accountId, paymentId);

	}
```

### TestCase 7: Get the newly created account details

* Get the account details using the account ID .
* Validate if the status is 200 .


```java
@Test(groups = "paymentsAPI", priority = 0)
		public void getAccountDetails() throws JSONException {
			log.info("Printing the newly created account details");
			getAccount(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.personId, PaymentsPostTestCases.accountId);
		}
```

### TestCase 8: Get the account balance
* Get the account balance using the account ID .
* Validate if the status is 200 .

```java
@Test(groups = "paymentsAPI", priority = 1)
		public void getAccountBalances() {
			log.info("Get the Account balances of the newely created account");
			getAccBalance(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId, PaymentsPostTestCases.currencyCode);

		}
```

### TestCase 9: Get account statement history
* Get the account statement using the account ID .
* Validate if the status is 200 .

```java
@Test(groups = "paymentsAPI", priority = 2)
		public void getAccountStatement() {
			log.info("Get the Account balances of any account number");
			getAccStatements(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId);
		}
```

### To run the above test cases, run the ModularBankPaymentsTests.xml as TestNG suite. This will invoke all the POST and GET APIs.

*There are POJO classes created for various Request and Response body objects, which will cater for multiple test scenarios*

## Test Output

The test output from TestNG suite.

![](src/main/resources/Images/TestOutput.PNG)

## CI/CD Pipeline Integration Using Jenkins

1. Run ModularBankPaymentsTests.xml from maven
2. Execute testng tests using maven build file pom.xml.
3. Include the below plugin configuration in your pom.xml.

```
        <plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.14.1</version>
				<configuration>
					<suiteXmlFiles>						<suiteXmlFile>ModularBankPaymentsTests.xml</suiteXmlFile>
					</suiteXmlFiles>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
		</plugins>
```
4. Run your project as **mvn clean install** on your IDE(eclipse). You will get the below output

![](src/main/resources/Images/MavenOutput.PNG)

5. Now try to run the project from command prompt. You should see the same result. 

![](src/main/resources/Images/CommandPrmptResult.PNG)

6. Now build and run a job in Jenkins
   *I have used the localhost:8080*
   
   > Results from Jenkins
   
   ![](src/main/resources/Images/jenkinsDashboard.PNG)
   
   
   ![](src/main/resources/Images/JenkinsBuildJob.PNG)
   
   
   ![](src/main/resources/Images/JenkinsBuild.PNG)
   
   
     
     
   
   
   
   
   







