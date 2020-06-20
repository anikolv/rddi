package com.rddi.registerapp.service;

import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;

import com.rddi.registerapp.dto.ValidateContractResponse;
import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceComment;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

public interface WebServiceManagement {

	List<WebService> searchWebServices(String searchTerm, WebServiceType webServiceType,
			WebServiceCategory webServiceCategory, ServiceProviderType serviceProviderType);
	
	byte[] generateClient(Long webServiceId, String clientType);
	
	byte[] generateServer(Long webServiceId, String serverType);

	WebServiceStatus getLastWebServiceStatus(WebService webService);
	
	Integer getLastMonthAvailabilityInPercentage(WebService webService);
	
	Integer getReliabilityInPercentage(WebService webService);
	
	void rateWebService(Long webServiceId, Long rating);
	
	OptionalDouble getAverageWebServiceRating(WebService webService);
	
	void commentWebService(Long webServiceId, String author, String comment);
	
	List<WebServiceComment> getWebServiceComments(Long webServiceId);
	
	ValidateContractResponse validateWebServiceContract(String contractUrl);
	
	void checkWebServiceAvailability(WebService webService) throws IOException;
	
	Long createWebService(String serviceProviderName, String serviceProviderDescription,
			ServiceProviderType serviceProviderType, String serviceProviderWebsite, String serviceProviderNameIconUrl,
			String apiName, String apiShortDescription, String apiDescription, WebServiceCategory apiCategory,
			WebServiceType apiType, String apiVersion, String apiDocUrl, String apiSpecUrl) throws IOException;
}
