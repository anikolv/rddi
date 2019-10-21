package com.rddi.registerapp.form;

import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

import lombok.Getter;
import lombok.Setter;

public class WebServiceSearchForm {

	private String searchTerm;
	private ServiceProviderType serviceProviderType;
	private WebServiceCategory apiCategory;
	private WebServiceType apiType;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public ServiceProviderType getServiceProviderType() {
		return serviceProviderType;
	}

	public void setServiceProviderType(ServiceProviderType serviceProviderType) {
		this.serviceProviderType = serviceProviderType;
	}

	public WebServiceCategory getApiCategory() {
		return apiCategory;
	}

	public void setApiCategory(WebServiceCategory apiCategory) {
		this.apiCategory = apiCategory;
	}

	public WebServiceType getApiType() {
		return apiType;
	}

	public void setApiType(WebServiceType apiType) {
		this.apiType = apiType;
	}

}
