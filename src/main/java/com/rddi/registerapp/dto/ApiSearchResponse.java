package com.rddi.registerapp.dto;

import java.util.ArrayList;
import java.util.List;

import com.rddi.registerapp.model.WebService;

public class ApiSearchResponse extends BaseApiResponse {

	private List<ApiWebService> webServices = new ArrayList<>();

	public ApiSearchResponse(List<ApiWebService> webServices) {
		this.webServices = webServices;
	}

	public List<ApiWebService> getWebServices() {
		return webServices;
	}

	public void setWebServices(List<ApiWebService> webServices) {
		this.webServices = webServices;
	}

}
