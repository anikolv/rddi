package com.rddi.registerapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rddi.registerapp.form.ClientSdkGenerationForm;
import com.rddi.registerapp.form.ServerStubGenerationForm;
import com.rddi.registerapp.form.WebServiceForm;
import com.rddi.registerapp.form.WebServiceSearchForm;
import com.rddi.registerapp.model.ServiceProvider;
import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;
import com.rddi.registerapp.repository.ServiceProviderRepository;
import com.rddi.registerapp.repository.WebServiceRepository;
import com.rddi.registerapp.service.WebServiceManagement;

@Controller
public class HomeController {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	
	@Autowired
	private WebServiceManagement webServiceManagement;
	
	@PostMapping(value="/search")
	public String search(Model model, @ModelAttribute(value="webServiceSearchForm") WebServiceSearchForm webServiceSearchForm) {
		List<WebService> webServices = webServiceManagement.searchWebServices(webServiceSearchForm.getSearchTerm(), webServiceSearchForm.getApiType(),
				webServiceSearchForm.getApiCategory(), webServiceSearchForm.getServiceProviderType());
		
		initModel(model);
		model.addAttribute("webServicesList", webServices);
		
		return "index";
	}
	
	@GetMapping(value="/api/details/{webServiceId}")
	public String apiDetails(@PathVariable("webServiceId") Long webServiceId, Model model) {
		WebService webService = webServiceRepository.findById(webServiceId).orElse(null);
		WebServiceStatus webServiceStatus = webServiceManagement.getLastWebServiceStatus(webService);
		Double lastMonthAvailabilityInPercentage = webServiceManagement.getLastMonthAvailabilityInPercentage(webService);
		Double reliabilityPercentage = webServiceManagement.getReliabilityInPercentage(webService);
		
		model.addAttribute("webService", webService);
		model.addAttribute("webServiceStatus", webServiceStatus);
		model.addAttribute("lastMonthAvailabilityInPercentage", lastMonthAvailabilityInPercentage);
		model.addAttribute("reliabilityPercentage", reliabilityPercentage);
		model.addAttribute("clientSdkGenerationForm", new ClientSdkGenerationForm());
		model.addAttribute("serverStubGenerationForm", new ServerStubGenerationForm());
		
		return "api-details";
	}
	
	private void initModel(Model model) {
		model.addAttribute("webServiceSearchForm", new WebServiceSearchForm());
		model.addAttribute("webServiceForm", new WebServiceForm());
		model.addAttribute("serviceProviderTypes", Arrays.asList(ServiceProviderType.values()));
		model.addAttribute("webServiceCategories", Arrays.asList(WebServiceCategory.values()));
		model.addAttribute("webServiceTypes",Arrays.asList(WebServiceType.values()));
	}
		
	@GetMapping(value="/")
	public String index(Model model) {
		initModel(model);
		
		return "index";
	}
	
	@PostMapping(value="/addApi")
	public String addApi(@ModelAttribute(value="webServiceForm") WebServiceForm webServiceForm) {
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
		
		return "index";
	}

}
