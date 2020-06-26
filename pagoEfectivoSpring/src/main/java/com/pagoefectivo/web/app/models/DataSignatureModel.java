package com.pagoefectivo.web.app.models;

public class DataSignatureModel {
	
	private int cip;
	private String currency;
	private double amount;

	public DataSignatureModel() {
		this.cip = 0;
		this.currency = "";
		this.amount = 0;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
