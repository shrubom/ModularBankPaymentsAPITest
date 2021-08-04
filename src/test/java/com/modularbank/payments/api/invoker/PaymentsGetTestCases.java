package com.modularbank.payments.api.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.modularbank.payments.api.utils.BaseClass;

public class PaymentsGetTestCases extends BaseClass{
	
	private static final Logger log = LogManager.getLogger(PaymentsGetTestCases.class);
	
	
	// Get the newly created account Details
		@Test(groups = "paymentsAPI", priority = 0)
		public void getAccountDetails() throws JSONException {
			log.info("Printing the newly created account details");
			getAccount(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.personId, PaymentsPostTestCases.accountId);
		}
	
	// Get the account balance
		@Test(groups = "paymentsAPI", priority = 1)
		public void getAccountBalances() {
			log.info("Get the Account balances of the newely created account");
			getAccBalance(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId, PaymentsPostTestCases.currencyCode);

		}
		
		
	// Get Account Statements
		@Test(groups = "paymentsAPI", priority = 2)
		public void getAccountStatement() {
			log.info("Get the Account balances of any account number");
			getAccStatements(PaymentsPostTestCases.tokenVal, PaymentsPostTestCases.accountId);
		}
		
		

}
