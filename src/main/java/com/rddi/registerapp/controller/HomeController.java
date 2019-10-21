package com.rddi.registerapp.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rddi.registerapp.model.enums.ServiceProviderType;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

@Controller
public class HomeController {
		
	@GetMapping(value="/")
	public String index(Model model) {
		model.addAttribute("serviceProviderTypes", Arrays.asList(ServiceProviderType.values()));
		model.addAttribute("webServiceCategories", Arrays.asList(WebServiceCategory.values()));
		model.addAttribute("webServiceTypes",Arrays.asList(WebServiceType.values()));
		
		return "index";
	}

}
