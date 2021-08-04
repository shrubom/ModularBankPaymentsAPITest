package com.modularbank.payments.api.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Source;

public class CreateTransactionResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String accountTransactionId;
	private String groupId;
	private String postingDate;
	private String valueDate;
	private String accountId;
	private String transactionTypeCode;
	private String directionCode;
	private String currencyCode;
	private Double amount;
	private Double initialBalanceAmount;
	private String filingCode;
	private String counterpartyName;
	private String counterpartyIban;
	private String counterpartyAccountNumber;
	private String counterpartyBic;
	private String details;
	private String referenceNumber;
	private String contractSource;
	private String merchantInfo;
	private String metaInfo;
	private String createdDTime;
	private String endToEndId;
	private String transactionDTime;
	private String reversalTransactionId;
	private Boolean customBookingAllowed;
	private Boolean reversed;
	private Boolean reversal;

	Source sourceTrans = new Source();

	public String getAccountTransactionId() {
		return accountTransactionId;
	}

	public void setAccountTransactionId(String accountTransactionId) {
		this.accountTransactionId = accountTransactionId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}

	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getInitialBalanceAmount() {
		return initialBalanceAmount;
	}

	public void setInitialBalanceAmount(Double initialBalanceAmount) {
		this.initialBalanceAmount = initialBalanceAmount;
	}

	public String getFilingCode() {
		return filingCode;
	}

	public void setFilingCode(String filingCode) {
		this.filingCode = filingCode;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterpartyIban() {
		return counterpartyIban;
	}

	public void setCounterpartyIban(String counterpartyIban) {
		this.counterpartyIban = counterpartyIban;
	}

	public String getCounterpartyAccountNumber() {
		return counterpartyAccountNumber;
	}

	public void setCounterpartyAccountNumber(String counterpartyAccountNumber) {
		this.counterpartyAccountNumber = counterpartyAccountNumber;
	}

	public String getCounterpartyBic() {
		return counterpartyBic;
	}

	public void setCounterpartyBic(String counterpartyBic) {
		this.counterpartyBic = counterpartyBic;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getContractSource() {
		return contractSource;
	}

	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}

	public String getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(String merchantInfo) {
		this.merchantInfo = merchantInfo;
	}

	public String getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}

	public String getCreatedDTime() {
		return createdDTime;
	}

	public void setCreatedDTime(String createdDTime) {
		this.createdDTime = createdDTime;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getTransactionDTime() {
		return transactionDTime;
	}

	public void setTransactionDTime(String transactionDTime) {
		this.transactionDTime = transactionDTime;
	}

	public String getReversalTransactionId() {
		return reversalTransactionId;
	}

	public void setReversalTransactionId(String reversalTransactionId) {
		this.reversalTransactionId = reversalTransactionId;
	}

	public Boolean getCustomBookingAllowed() {
		return customBookingAllowed;
	}

	public void setCustomBookingAllowed(Boolean customBookingAllowed) {
		this.customBookingAllowed = customBookingAllowed;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getReversal() {
		return reversal;
	}

	public void setReversal(Boolean reversal) {
		this.reversal = reversal;
	}

	public Source getSourceTrans() {
		return sourceTrans;
	}

	public void setSourceTrans(Source sourceTrans) {
		this.sourceTrans = sourceTrans;
	}

}
