package com.rddi.registerapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
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

}
