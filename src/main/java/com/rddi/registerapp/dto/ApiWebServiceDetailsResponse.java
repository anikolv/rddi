package com.rddi.registerapp.dto;

public class ApiWebServiceDetailsResponse extends BaseApiResponse {

	private ApiWebServiceDetails webService;

	public ApiWebServiceDetails getWebService() {
		return webService;
	}

	public void setWebService(ApiWebServiceDetails webService) {
		this.webService = webService;
	}

	public ApiWebServiceDetailsResponse(ApiWebServiceDetails webService) {
		this.webService = webService;
	}

}
