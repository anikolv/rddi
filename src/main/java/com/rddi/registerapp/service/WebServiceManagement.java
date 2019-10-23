package com.rddi.registerapp.service;

import java.util.List;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

public interface WebServiceManagement {

	public List<WebService> searchWebServices(String searchTerm, WebServiceType webServiceType,
			WebServiceCategory webServiceCategory, ServiceProviderType serviceProviderType);
	
	public byte[] generateClient(Long webServiceId, String clientType);
	
	public byte[] generateServer(Long webServiceId, String serverType);

}
