package com.rddi.registerapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rddi.registerapp.dto.AddApiRequest;
import com.rddi.registerapp.dto.ApiCreateResponse;
import com.rddi.registerapp.dto.ApiSearchResponse;
import com.rddi.registerapp.dto.ApiWebService;
import com.rddi.registerapp.dto.ApiWebServiceDetails;
import com.rddi.registerapp.dto.ApiWebServiceDetailsResponse;
import com.rddi.registerapp.dto.BaseApiResponse;
import com.rddi.registerapp.exception.ApiValidationException;
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
	
	@PostMapping(value = "/create")
	public ResponseEntity<ApiCreateResponse> addApi(@RequestBody AddApiRequest apiRequest)
			throws IOException, ApiValidationException {
		apiRequest.validate(webServiceManagement);

		Long webServiceId = webServiceManagement.createWebService(apiRequest.getServiceProviderName(),
				apiRequest.getServiceProviderDescription(), apiRequest.getServiceProviderType(),
				apiRequest.getServiceProviderWebsite(), apiRequest.getServiceProviderNameIconUrl(),
				apiRequest.getApiName(), apiRequest.getApiShortDescription(), apiRequest.getApiDescription(),
				apiRequest.getApiCategory(), apiRequest.getApiType(), apiRequest.getApiVersion(),
				apiRequest.getApiDocUrl(), apiRequest.getApiSpecUrl());

		return new ResponseEntity<ApiCreateResponse>(new ApiCreateResponse(webServiceId), HttpStatus.CREATED);
	}
	
	@ExceptionHandler(ApiValidationException.class)
	public ResponseEntity<BaseApiResponse> handleApiValidationException(ApiValidationException ex) {
		return new ResponseEntity<BaseApiResponse>(new BaseApiResponse(Boolean.FALSE, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<BaseApiResponse> handleIOException(IOException ex) {
		return new ResponseEntity<BaseApiResponse>(new BaseApiResponse(Boolean.FALSE, ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<BaseApiResponse> handleRuntimeException(RuntimeException ex) {
		return new ResponseEntity<BaseApiResponse>(new BaseApiResponse(Boolean.FALSE, ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
