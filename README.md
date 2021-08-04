# Modular Bank Payments API Test

## Steps Performed
	
	1. Perform manual testing using postman
	2. Create maven project and set up TESTNG automation framework on the same 
	3. Define and run testcases .
	
## Test cases

### Testcase 1: Get the JWT authentication

	> Validate the status code is **200** <br>
	Save the token for further use .
	
### Testcase 2: Create Customer details 

	Implement object.properties file to get the required input for the request body .<br>
	Validate if the status is 200 .<br>
	fetch the person Id to be used in the CreatAccountAPI .
	
	> code samples for the same @Util package
	
	```java
	
	
		response = request.body(custReq).when().post(Constants.person_Api_URL);
		String strResponse = response.asString();
		CreateCustomerResponse customerObj = new CreateCustomerResponse();
		JSONObject jsonCustObj = new JSONObject(strResponse);
		personId = jsonCustObj.getJSONObject("data").getString("personId");
		givenName = jsonCustObj.getJSONObject("data").getString("givenName");
		fullName = jsonCustObj.getJSONObject("data").getString("fullName");
		passportIdNumber = jsonCustObj.getJSONObject("data").getString("idNumber");
	
	
	```
	
### Testcase 3: Create Account for the new customer

* Use the PersonId received from the previous post API and provide the appropriate request body .
* Validate that the status is 200 
* Fetch account id for the payments and transactions api




 
	