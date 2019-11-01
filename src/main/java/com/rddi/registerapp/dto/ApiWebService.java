package com.rddi.registerapp.dto;

import com.rddi.registerapp.model.WebService;

public class ApiWebService {

	private Long id;
	private String iconUrl;
	private String name;
	private String shortDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public static ApiWebService from(WebService webService) {
		ApiWebService to = new ApiWebService();
		to.setId(webService.getId());
		to.setIconUrl(webService.getServiceProvider().getIconUrl());
		to.setName(webService.getName());
		to.setShortDescription(webService.getShortDescription());
		
		return to;
	}

}
