package com.pagoefectivo.web.app.models;

import com.pagoefectivo.web.app.models.DataCIPModel;

public class GeneracionCIPModel {

	private int code;
	private String message;
	private DataCIPModel data;
	
	public GeneracionCIPModel() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataCIPModel getData() {
		return data;
	}

	public void setData(DataCIPModel data) {
		this.data = data;
	}
}
