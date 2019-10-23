package com.rddi.registerapp.dto;

public class GenerateServerRequest {

	String openAPIUrl;

	public GenerateServerRequest(String openAPIUrl) {
		this.openAPIUrl = openAPIUrl;
	}

	public String getOpenAPIUrl() {
		return openAPIUrl;
	}

	public void setOpenAPIUrl(String openAPIUrl) {
		this.openAPIUrl = openAPIUrl;
	}

}
