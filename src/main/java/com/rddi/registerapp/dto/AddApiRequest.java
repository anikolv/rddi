package com.rddi.registerapp.dto;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.rddi.registerapp.dto.ValidateContractResponse.SchemaValidationMessage;
import com.rddi.registerapp.exception.ApiValidationException;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.service.WebServiceManagement;

import io.swagger.annotations.ApiModelProperty;

public class AddApiRequest {

	@ApiModelProperty(example = "Service provider")
	private String serviceProviderName;
	
	@ApiModelProperty(example = "Service provider description")
	private String serviceProviderDescription;
	
	@ApiModelProperty(example = "BUSINESS_ORGANIZATION, SOFTWARE_FOUNDATION, PRIVATE_SERVICE_PROVIDER, OTHER")
	private ServiceProviderType serviceProviderType;
	
	@ApiModelProperty(example = "gmail.com")
	private String serviceProviderWebsite;
	
	@ApiModelProperty(example = "https://avatars3.githubusercontent.com/u/16343502?s=400&v=4")
	private String serviceProviderNameIconUrl;

	@ApiModelProperty(example = "Example API")
	private String apiName;
	
	@ApiModelProperty(example = "Example API short description")
	private String apiShortDescription;
	
	@ApiModelProperty(example = "Example API description")
	private String apiDescription;
	
	@ApiModelProperty(example = "FINTECH, HEALTHCARE, WEATHER_AND_FORECASTS, TELCO, TRANSPORT, EDUCATION, UTILITIES, IT, OTHER")
	private WebServiceCategory apiCategory;
	
	@ApiModelProperty(example = "SANDBOX, OFF_THE_SHELF")
	private WebServiceType apiType;
	
	@ApiModelProperty(example = "1.0.0")
	private String apiVersion;
	
	@ApiModelProperty(example = "API reference documentation URL")
	private String apiDocUrl;
	
	@ApiModelProperty(example = "URL to the Swagger 2.0 or OpenAPI v3 specification in either JSON or YAML format")
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
		
		if (validationResponse.getSchemaValidationMessages() != null) {
			Optional<SchemaValidationMessage> schemaValidationMessage = 
					validationResponse.getSchemaValidationMessages()
					.stream().filter(message -> message.getLevel().equals("error"))
					.findFirst();
			
			if (schemaValidationMessage.isPresent()) {
				throw new ApiValidationException(schemaValidationMessage.get().getMessage());
			}
		}
	}
	
	public static AddApiRequest from(AddApiRequest from) {
		AddApiRequest to = new AddApiRequest();
		BeanUtils.copyProperties(from, to);
		return to;
	}

}
