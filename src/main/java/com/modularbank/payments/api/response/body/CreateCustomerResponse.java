package com.modularbank.payments.api.response.body;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.modularbank.payments.api.model.Address;
import com.modularbank.payments.api.model.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerResponse {

	private String givenName;
	private String surname;
	private String middleName;
	private String birthDate;
	private String personTypeCode;
	private String sex;
	private String email;
	private String phoneNumber;
	private String phoneCountryCode;
	private String educationCode;
	private String activityCode;
	private String housingTypeCode;
	private String buildingTypeCode;
	private String businessAreaCode;
	private String maritalStatusCode;
	private Integer dependantPersons;
	private String employmentTimeCode;
	private String customerType;
	private String nationality;
	private String placeOfBirth;
	private String countryOfBirth;
	private String language;
	private String taxResidencyCountry;
	private String identificationNumber;
	private Integer fixedEmploymentLength;
	private Boolean usResident;
	private Boolean pep;
	// response
	private String personId;
	private String addressId;
	private String docuId;
	private String fullName;

	// objects

	private List<Address> addresses = new ArrayList<Address>();
	private Document document = new Document();
	
	
	

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPersonTypeCode() {
		return personTypeCode;
	}

	public void setPersonTypeCode(String personTypeCode) {
		this.personTypeCode = personTypeCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneCountryCode() {
		return phoneCountryCode;
	}

	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getHousingTypeCode() {
		return housingTypeCode;
	}

	public void setHousingTypeCode(String housingTypeCode) {
		this.housingTypeCode = housingTypeCode;
	}

	public String getBuildingTypeCode() {
		return buildingTypeCode;
	}

	public void setBuildingTypeCode(String buildingTypeCode) {
		this.buildingTypeCode = buildingTypeCode;
	}

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public String getMaritalStatusCode() {
		return maritalStatusCode;
	}

	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	public Integer getDependantPersons() {
		return dependantPersons;
	}

	public void setDependantPersons(Integer dependantPersons) {
		this.dependantPersons = dependantPersons;
	}

	public String getEmploymentTimeCode() {
		return employmentTimeCode;
	}

	public void setEmploymentTimeCode(String employmentTimeCode) {
		this.employmentTimeCode = employmentTimeCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTaxResidencyCountry() {
		return taxResidencyCountry;
	}

	public void setTaxResidencyCountry(String taxResidencyCountry) {
		this.taxResidencyCountry = taxResidencyCountry;
	}

	public Integer getFixedEmploymentLength() {
		return fixedEmploymentLength;
	}

	public void setFixedEmploymentLength(Integer fixedEmploymentLength) {
		this.fixedEmploymentLength = fixedEmploymentLength;
	}

	public Boolean getUsResident() {
		return usResident;
	}

	public void setUsResident(Boolean usResident) {
		this.usResident = usResident;
	}

	public Boolean getPep() {
		return pep;
	}

	public void setPep(Boolean pep) {
		this.pep = pep;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getDocuId() {
		return docuId;
	}

	public void setDocuId(String docuId) {
		this.docuId = docuId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
