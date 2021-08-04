package com.modularbank.payments.api.response.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modularbank.payments.api.model.Source;

public class CreatePaymentResponse {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	
	private String paymentId;
	private String accountId;
	private Double amount;
	private Double feeAmount;
	private String iban;
	private String bic;
	private String name;	
	private String currencyCode;
	private String directionCode;
	private Double postingDate;
	private Double effectiveDate;
	private String counterpartyIban;
	private String counterpartyOriginalIban;
	private String counterpartyName;
	private String counterpartyBic;
	private String description;
	private Double sourceReferenceNumber;
	private Double payerPaymentIdentifier;
	private String beneficiaryPaymentIdentifier;
	private String referenceNumber;
	private String statusCode;	
	private String errorCode;
	private String returnReason;
	private Double paymentTypeCode;
	private Double endToEndId;
	private String lastStatusRequestDate;
	private String cancellationRequestStatusCode;
	private String cancelRefuseReason;	
	private String cancelReason;
	private Double transferType;
	private Double countryCode;
	private String contractSource;
	private String paymentSettlementDate;
	private String returnSettlementDate;	
	private String insertedDateTime;
	
	Source source = new Source();

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public Double getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Double postingDate) {
		this.postingDate = postingDate;
	}

	public Double getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Double effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCounterpartyIban() {
		return counterpartyIban;
	}

	public void setCounterpartyIban(String counterpartyIban) {
		this.counterpartyIban = counterpartyIban;
	}

	public String getCounterpartyOriginalIban() {
		return counterpartyOriginalIban;
	}

	public void setCounterpartyOriginalIban(String counterpartyOriginalIban) {
		this.counterpartyOriginalIban = counterpartyOriginalIban;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterpartyBic() {
		return counterpartyBic;
	}

	public void setCounterpartyBic(String counterpartyBic) {
		this.counterpartyBic = counterpartyBic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSourceReferenceNumber() {
		return sourceReferenceNumber;
	}

	public void setSourceReferenceNumber(Double sourceReferenceNumber) {
		this.sourceReferenceNumber = sourceReferenceNumber;
	}

	public Double getPayerPaymentIdentifier() {
		return payerPaymentIdentifier;
	}

	public void setPayerPaymentIdentifier(Double payerPaymentIdentifier) {
		this.payerPaymentIdentifier = payerPaymentIdentifier;
	}

	public String getBeneficiaryPaymentIdentifier() {
		return beneficiaryPaymentIdentifier;
	}

	public void setBeneficiaryPaymentIdentifier(String beneficiaryPaymentIdentifier) {
		this.beneficiaryPaymentIdentifier = beneficiaryPaymentIdentifier;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public Double getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(Double paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public Double getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(Double endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getLastStatusRequestDate() {
		return lastStatusRequestDate;
	}

	public void setLastStatusRequestDate(String lastStatusRequestDate) {
		this.lastStatusRequestDate = lastStatusRequestDate;
	}

	public String getCancellationRequestStatusCode() {
		return cancellationRequestStatusCode;
	}

	public void setCancellationRequestStatusCode(String cancellationRequestStatusCode) {
		this.cancellationRequestStatusCode = cancellationRequestStatusCode;
	}

	public String getCancelRefuseReason() {
		return cancelRefuseReason;
	}

	public void setCancelRefuseReason(String cancelRefuseReason) {
		this.cancelRefuseReason = cancelRefuseReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Double getTransferType() {
		return transferType;
	}

	public void setTransferType(Double transferType) {
		this.transferType = transferType;
	}

	public Double getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Double countryCode) {
		this.countryCode = countryCode;
	}

	public String getContractSource() {
		return contractSource;
	}

	public void setContractSource(String contractSource) {
		this.contractSource = contractSource;
	}

	public String getPaymentSettlementDate() {
		return paymentSettlementDate;
	}

	public void setPaymentSettlementDate(String paymentSettlementDate) {
		this.paymentSettlementDate = paymentSettlementDate;
	}

	public String getReturnSettlementDate() {
		return returnSettlementDate;
	}

	public void setReturnSettlementDate(String returnSettlementDate) {
		this.returnSettlementDate = returnSettlementDate;
	}

	public String getInsertedDateTime() {
		return insertedDateTime;
	}

	public void setInsertedDateTime(String insertedDateTime) {
		this.insertedDateTime = insertedDateTime;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
	
	

}
