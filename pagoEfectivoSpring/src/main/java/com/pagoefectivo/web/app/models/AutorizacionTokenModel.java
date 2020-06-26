package com.pagoefectivo.web.app.models;

public class AutorizacionTokenModel {

	private String currency;
	private String amount;
	private int transactionCode;
	private String dateExpiry;
	private String paymentConcept;
	private String additionalData;
	private String adminEmail;
	private String userEmail;
	private String userId;
	private String userName;
	private String userLastName;
	private int userUbigeo;
	private String userCountry;
	private String userDocumentType;
	private int userDocumentNumber;
	private int userPhone;
	private String userCodeCountry;
	private String serviceId;

	public AutorizacionTokenModel() {
		super();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(int transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(String dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getPaymentConcept() {
		return paymentConcept;
	}

	public void setPaymentConcept(String paymentConcept) {
		this.paymentConcept = paymentConcept;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getUserUbigeo() {
		return userUbigeo;
	}

	public void setUserUbigeo(int userUbigeo) {
		this.userUbigeo = userUbigeo;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserDocumentType() {
		return userDocumentType;
	}

	public void setUserDocumentType(String userDocumentType) {
		this.userDocumentType = userDocumentType;
	}

	public int getUserDocumentNumber() {
		return userDocumentNumber;
	}

	public void setUserDocumentNumber(int userDocumentNumber) {
		this.userDocumentNumber = userDocumentNumber;
	}

	public int getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserCodeCountry() {
		return userCodeCountry;
	}

	public void setUserCodeCountry(String userCodeCountry) {
		this.userCodeCountry = userCodeCountry;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
