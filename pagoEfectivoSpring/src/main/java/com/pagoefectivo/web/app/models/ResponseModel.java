package com.pagoefectivo.web.app.models;

public class ResponseModel {
	
	private String code;
	private String message;
	private DataResponseModel data;
	
	public ResponseModel() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DataResponseModel getData() {
		return data;
	}
	public void setData(DataResponseModel data) {
		this.data = data;
	}
	
}
