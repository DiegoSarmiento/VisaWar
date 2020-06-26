package com.pagoefectivo.web.app.models;

public class SignatureModel {
	
	private String eventType;
	private String operationNumber;
	private DataSignatureModel data;

	public SignatureModel() {
		this.eventType = "";
		this.operationNumber= "";
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}

	public DataSignatureModel getData() {
		return data;
	}

	public void setData(DataSignatureModel data) {
		this.data = data;
	}

}
