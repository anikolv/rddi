package com.rddi.registerapp.dto;

import java.util.Optional;

import org.springframework.util.StringUtils;

import com.rddi.registerapp.dto.ValidateContractResponse.SchemaValidationMessage;
import com.rddi.registerapp.exception.ApiValidationException;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.service.WebServiceManagement;

public class AddApiRequest {

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
	
	public void validate(WebServiceManagement webServiceManagement) throws ApiValidationException {
		// service provider validation
		
		if (!StringUtils.hasLength(serviceProviderName)) {
			throw new ApiValidationException("Service provider name is missing");
		}
		
		if (!StringUtils.hasLength(serviceProviderDescription)) {
			throw new ApiValidationException("Service provider description is missing");
		}
		
		if (!StringUtils.hasLength(serviceProviderNameIconUrl)) {
			throw new ApiValidationException("Service provider icon url is missing");
		}
		
		// web service validation
		
		if (!StringUtils.hasLength(apiName)) {
			throw new ApiValidationException("API name is missing");
		}
		
		if (!StringUtils.hasLength(apiShortDescription)) {
			throw new ApiValidationException("API short description is missing");
		}
		
		if (!StringUtils.hasLength(apiDescription)) {
			throw new ApiValidationException("API description is missing");
		}
		
		if (!StringUtils.hasLength(apiVersion)) {
			throw new ApiValidationException("API version is missing");
		}
		
		if (!StringUtils.hasLength(apiSpecUrl)) {
			throw new ApiValidationException("API contract url is missing");
		}
		
		// external api contract validation
		
		ValidateContractResponse validationResponse = webServiceManagement
				.validateWebServiceContract(apiSpecUrl);
		
		Optional<SchemaValidationMessage> schemaValidationMessage = 
				validationResponse.getSchemaValidationMessages()
				.stream().filter(message -> message.getLevel().equals("error"))
				.findFirst();
		
		if (schemaValidationMessage.isPresent()) {
			throw new ApiValidationException(schemaValidationMessage.get().getMessage());
		}
	}

}
