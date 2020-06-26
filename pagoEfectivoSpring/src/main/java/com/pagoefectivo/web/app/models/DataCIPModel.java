package com.pagoefectivo.web.app.models;

public class DataCIPModel {
	private int cip;
	private String currency;
	private Double amount;
	private String transactionCode;
	private String dateExpiry;
	private String cipUrl;
	
	public DataCIPModel() {
		super();
	}

	public int getCip() {
		return cip;
	}

	public void setCip(int cip) {
		this.cip = cip;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(String dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getCipUrl() {
		return cipUrl;
	}

	public void setCipUrl(String cipUrl) {
		this.cipUrl = cipUrl;
	}

}
