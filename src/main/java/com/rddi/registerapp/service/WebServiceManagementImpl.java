package com.rddi.registerapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.rddi.registerapp.dto.GenerateClientRequest;
import com.rddi.registerapp.dto.GenerateClientResponse;
import com.rddi.registerapp.dto.GenerateServerRequest;
import com.rddi.registerapp.dto.GenerateServerResponse;
import com.rddi.registerapp.model.QWebService;
import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.predicate.WebServiceStatusPredicates;
import com.rddi.registerapp.repository.WebServiceRepository;
import com.rddi.registerapp.repository.WebServiceStatusRepository;

@Service
public class WebServiceManagementImpl implements WebServiceManagement {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
	@Autowired
	private WebServiceStatusRepository webServiceStatusRepository;
	
	@Value( "${openapi.generator.url}" )
	private String openApiGeneratorUrl;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<WebService> searchWebServices(String searchTerm, WebServiceType webServiceType,
			WebServiceCategory webServiceCategory, ServiceProviderType serviceProviderType) {
		try {
			QWebService webService = QWebService.webService;
			
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			
			if (StringUtils.hasLength(searchTerm)) {
				booleanBuilder
				.and(webService.name.likeIgnoreCase(searchTerm))
				.or(webService.description.likeIgnoreCase(searchTerm))
				.or(webService.serviceProvider.name.likeIgnoreCase(searchTerm))
				.or(webService.serviceProvider.description.likeIgnoreCase(searchTerm));
			}
			
			if (webServiceCategory != null) {
				booleanBuilder.and(webService.category.eq(webServiceCategory));
			}
			
			if (webServiceType != null) {
				booleanBuilder.and(webService.type.eq(webServiceType));
			}
			
			if (serviceProviderType != null) {
				booleanBuilder.and(webService.serviceProvider.type.eq(serviceProviderType));
			}

			Iterable<WebService> result = webServiceRepository.findAll(booleanBuilder, webService.name.desc());
			
			return Lists.newArrayList(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public byte[] generateClient(Long webServiceId, String clientType) {
		WebService webService = webServiceRepository.findById(webServiceId).orElse(null);
		String specUrl = webService.getOpenApiContract();
		
		HttpEntity<GenerateClientRequest> request = new HttpEntity<>(new GenerateClientRequest(specUrl));
		GenerateClientResponse response = restTemplate.postForObject(openApiGeneratorUrl + "/clients/" + clientType,
				request, GenerateClientResponse.class);
		
		String sdkFileDownloadUrl = response.getLink();
		
		byte[] downloadedBytes = restTemplate.getForObject(sdkFileDownloadUrl, byte[].class);
		
		return downloadedBytes;
	}

	@Override
	public byte[] generateServer(Long webServiceId, String serverType) {
		WebService webService = webServiceRepository.findById(webServiceId).orElse(null);
		String specUrl = webService.getOpenApiContract();
		
		HttpEntity<GenerateServerRequest> request = new HttpEntity<>(new GenerateServerRequest(specUrl));
		GenerateServerResponse response = restTemplate.postForObject(openApiGeneratorUrl + "/servers/" + serverType,
				request, GenerateServerResponse.class);
		
		String sdkFileDownloadUrl = response.getLink();
		
		byte[] downloadedBytes = restTemplate.getForObject(sdkFileDownloadUrl, byte[].class);
		
		return downloadedBytes;
	}
	
	@Override
	public WebServiceStatus getLastWebServiceStatus(WebService webService) {
		WebServiceStatus webServiceStatus = webServiceStatusRepository.findTop1ByWebService(webService);
		return webServiceStatus;
	}
	
	@Override
	public Double getLastMonthAvailabilityInPercentage(WebService webService) {
		Date lastMonthDate = new DateTime().minusMonths(1).toDate();
		List<WebServiceStatus> statuses = webServiceStatusRepository.findByWebServiceAndCheckedAtAfter(webService,
				lastMonthDate);
		
		long failedChecks = statuses
				.stream()
				.filter(WebServiceStatusPredicates.available())
				.count();
		
		Double lastMonthAvailabilityPercentage = (Long.valueOf(failedChecks).doubleValue() / statuses.size()) * 100;
				
		return lastMonthAvailabilityPercentage;
	}
	
	public static void main(String[] args) {
		long test = 19;
		int test2 = 20;
		
		Double lastMonthAvailabilityPercentage = (Long.valueOf(test).doubleValue() / test2) * 100;

		
		System.out.println(lastMonthAvailabilityPercentage);
	}
}
