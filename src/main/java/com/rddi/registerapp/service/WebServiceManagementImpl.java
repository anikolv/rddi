package com.rddi.registerapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.repository.WebServiceRepository;

@Service
public class WebServiceManagementImpl implements WebServiceManagement {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
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
	
	public static void main(String[] args) {
		String clientsString = "ada-server, aspnetcore, cpp-pistache-server, cpp-qt5-qhttpengine-server, cpp-restbed-server, csharp-nancyfx, erlang-server, fsharp-functions, fsharp-giraffe-server, go-gin-server, go-server, graphql-nodejs-express-server, haskell, java-inflector, java-msf4j, java-pkmst, java-play-framework, java-undertow-server, java-vertx, jaxrs-cxf, jaxrs-cxf-cdi, jaxrs-cxf-extended, jaxrs-jersey, jaxrs-resteasy, jaxrs-resteasy-eap, jaxrs-spec, kotlin-server, kotlin-spring, kotlin-vertx, nodejs-express-server, nodejs-server-deprecated, php-laravel, php-lumen, php-silex, php-slim, php-symfony, php-ze-ph, python-aiohttp, python-blueplanet, python-flask, ruby-on-rails, ruby-sinatra, rust-server, scala-finch, scala-lagom-server, scala-play-server, scalatra, spring";
		String[] clients = clientsString.split(", ");
		for (int i=0; i<clients.length; i++) {
			String client = clients[i];
			String result = "<option id=\"" + client + "\"  value=\"" + client + "\">" + client.substring(0,1).toUpperCase() + client.substring(1).toLowerCase() + "</option>";
			System.out.println(result);
		}
	}

}
