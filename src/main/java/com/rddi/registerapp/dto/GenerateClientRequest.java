package com.rddi.registerapp.dto;

public class GenerateClientRequest {

	String openAPIUrl;

	public GenerateClientRequest(String openAPIUrl) {
		super();
		this.openAPIUrl = openAPIUrl;
	}

	public String getOpenAPIUrl() {
		return openAPIUrl;
	}

	public void setOpenAPIUrl(String openAPIUrl) {
		this.openAPIUrl = openAPIUrl;
	}

}
