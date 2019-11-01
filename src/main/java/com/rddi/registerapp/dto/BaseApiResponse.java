package com.rddi.registerapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class BaseApiResponse {

	private Boolean success = Boolean.TRUE;
	private String errorMessage;
	
	public BaseApiResponse() {}
	
	public BaseApiResponse(Boolean success, String errorMessage) {
		this.success = success;
		this.errorMessage = errorMessage;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
