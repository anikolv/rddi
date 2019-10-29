package com.rddi.registerapp.service;

import java.util.List;
import java.util.OptionalDouble;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceComment;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

public interface WebServiceManagement {

	public List<WebService> searchWebServices(String searchTerm, WebServiceType webServiceType,
			WebServiceCategory webServiceCategory, ServiceProviderType serviceProviderType);
	
	public byte[] generateClient(Long webServiceId, String clientType);
	
	public byte[] generateServer(Long webServiceId, String serverType);

	WebServiceStatus getLastWebServiceStatus(WebService webService);
	
	Double getLastMonthAvailabilityInPercentage(WebService webService);
	
	Double getReliabilityInPercentage(WebService webService);
	
	void rateWebService(Long webServiceId, Long rating);
	
	OptionalDouble getAverageWebServiceRating(WebService webService);
	
	void commentWebService(Long webServiceId, String author, String comment);
	
	List<WebServiceComment> getWebServiceComments(Long webServiceId);
}
