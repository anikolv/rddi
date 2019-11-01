package com.rddi.registerapp.dto;

public class ApiCreateResponse extends BaseApiResponse {

	private Long webServiceId;

	public ApiCreateResponse(Long webServiceId) {
		super();
		this.webServiceId = webServiceId;
	}

	public Long getWebServiceId() {
		return webServiceId;
	}

	public void setWebServiceId(Long webServiceId) {
		this.webServiceId = webServiceId;
	}

}
