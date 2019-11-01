package com.rddi.registerapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rddi.registerapp.dto.ApiSearchResponse;
import com.rddi.registerapp.dto.ApiWebService;
import com.rddi.registerapp.dto.ApiWebServiceDetails;
import com.rddi.registerapp.dto.ApiWebServiceDetailsResponse;
import com.rddi.registerapp.form.WebServiceForm;
import com.rddi.registerapp.model.ServiceProvider;
import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.repository.ServiceProviderRepository;
import com.rddi.registerapp.repository.WebServiceRepository;
import com.rddi.registerapp.service.WebServiceManagement;

@RestController
@RequestMapping(value = "/api")
public class RestEntrypointController {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
	private WebServiceManagement webServiceManagement;
	
	@GetMapping(value="/search")
	public ResponseEntity<ApiSearchResponse> search(
			@RequestParam("searchTerm") Optional<String> searchTerm,
			@RequestParam("serviceProviderType") Optional<ServiceProviderType> serviceProviderType,
			@RequestParam("apiCategory") Optional<WebServiceCategory> apiCategory,
			@RequestParam("apiType") Optional<WebServiceType> apiType) {
		List<WebService> webServices = webServiceManagement.searchWebServices(
				searchTerm.orElse(null), 
				apiType.orElse(null),
				apiCategory.orElse(null), 
				serviceProviderType.orElse(null));
		
		List<ApiWebService> apiWebServices = new ArrayList<>();
		webServices.forEach(ws -> apiWebServices.add(ApiWebService.from(ws)));
				
		return new ResponseEntity<ApiSearchResponse>(new ApiSearchResponse(apiWebServices), HttpStatus.OK);
	}
	
	@GetMapping(value="/details")
	public ResponseEntity<ApiWebServiceDetailsResponse> apiDetails(@RequestParam("webServiceId") Long webServiceId) {
		WebService webService = webServiceRepository.findById(webServiceId).orElse(null);
		
		ApiWebServiceDetails apiWebService = ApiWebServiceDetails.from(webService);
		
		WebServiceStatus webServiceStatus = webServiceManagement.getLastWebServiceStatus(webService);
		Double lastMonthAvailabilityInPercentage = webServiceManagement.getLastMonthAvailabilityInPercentage(webService);
		Double reliabilityPercentage = webServiceManagement.getReliabilityInPercentage(webService);
		OptionalDouble averageRating = webServiceManagement.getAverageWebServiceRating(webService);
		
		apiWebService.addEvaluationDetails(webServiceStatus, lastMonthAvailabilityInPercentage, reliabilityPercentage, averageRating.orElse(0));
		
		return new ResponseEntity<ApiWebServiceDetailsResponse>(new ApiWebServiceDetailsResponse(apiWebService),
				HttpStatus.OK);
	}
	
	//maybe add as well?
	@PostMapping(value="/add")
	public String addApi(@ModelAttribute(value = "webServiceForm") WebServiceForm webServiceForm, Model model)
			throws IOException {
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setName(webServiceForm.getServiceProviderName());
		serviceProvider.setDescription(webServiceForm.getServiceProviderDescription());
		serviceProvider.setType(webServiceForm.getServiceProviderType());
		serviceProvider.setWebsite(webServiceForm.getServiceProviderWebsite());
		serviceProvider.setIconUrl(webServiceForm.getServiceProviderNameIconUrl());

		serviceProviderRepository.save(serviceProvider);

		WebService webService = new WebService();
		webService.setName(webServiceForm.getApiName());
		webService.setShortDescription(webServiceForm.getApiShortDescription());
		webService.setDescription(webServiceForm.getApiDescription());
		webService.setCategory(webServiceForm.getApiCategory());
		webService.setType(webServiceForm.getApiType());
		webService.setVersion(webServiceForm.getApiVersion());
		webService.setDocumentationUrl(webServiceForm.getApiDocUrl());
		webService.setOpenApiContract(webServiceForm.getApiSpecUrl());

		webService.addServiceProvider(serviceProvider);

		webServiceRepository.save(webService);

		webServiceManagement.checkWebServiceAvailability(webService);

		return "index";
	}

}
