package com.rddi.registerapp.form;

import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

import lombok.Getter;
import lombok.Setter;

public class WebServiceForm {

	private String serviceProviderName;
	private String serviceProviderDescription;
	private ServiceProviderType serviceProviderType;
	private String serviceProviderWebsite;
	private String serviceProviderNameIconUrl;

	private String apiName;
	private String apiShortDescription;
	private String apiDescription;
	private WebServiceCategory apiCategory;
	private WebServiceType apiType;
	private String apiVersion;
	private String apiDocUrl;
	private String apiSpecUrl;

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getServiceProviderDescription() {
		return serviceProviderDescription;
	}

	public void setServiceProviderDescription(String serviceProviderDescription) {
		this.serviceProviderDescription = serviceProviderDescription;
	}

	public ServiceProviderType getServiceProviderType() {
		return serviceProviderType;
	}

	public void setServiceProviderType(ServiceProviderType serviceProviderType) {
		this.serviceProviderType = serviceProviderType;
	}

	public String getServiceProviderWebsite() {
		return serviceProviderWebsite;
	}

	public void setServiceProviderWebsite(String serviceProviderWebsite) {
		this.serviceProviderWebsite = serviceProviderWebsite;
	}

	public String getServiceProviderNameIconUrl() {
		return serviceProviderNameIconUrl;
	}

	public void setServiceProviderNameIconUrl(String serviceProviderNameIconUrl) {
		this.serviceProviderNameIconUrl = serviceProviderNameIconUrl;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiShortDescription() {
		return apiShortDescription;
	}

	public void setApiShortDescription(String apiShortDescription) {
		this.apiShortDescription = apiShortDescription;
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

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getApiDescription() {
		return apiDescription;
	}

	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
	}

	public String getApiDocUrl() {
		return apiDocUrl;
	}

	public void setApiDocUrl(String apiDocUrl) {
		this.apiDocUrl = apiDocUrl;
	}

	public String getApiSpecUrl() {
		return apiSpecUrl;
	}

	public void setApiSpecUrl(String apiSpecUrl) {
		this.apiSpecUrl = apiSpecUrl;
	}

}
