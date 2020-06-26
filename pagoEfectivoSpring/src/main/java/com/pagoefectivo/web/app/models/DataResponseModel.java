package com.pagoefectivo.web.app.models;

public class DataResponseModel {
	private String token;
	private String codeService;
	private String tokenStart;
	private String tokenExpires;
	
	public DataResponseModel() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	public String getTokenStart() {
		return tokenStart;
	}
	public void setTokenStart(String tokenStart) {
		this.tokenStart = tokenStart;
	}
	public String getTokenExpires() {
		return tokenExpires;
	}
	public void setTokenExpires(String tokenExpires) {
		this.tokenExpires = tokenExpires;
	}
}
