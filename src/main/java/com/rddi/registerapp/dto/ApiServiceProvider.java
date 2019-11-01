package com.rddi.registerapp.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rddi.registerapp.model.ServiceProvider;
import com.rddi.registerapp.model.enums.ServiceProviderType;

@JsonInclude(Include.NON_EMPTY)
public class ApiServiceProvider {

	private Long id;
	private String name;
	private String description;
	private ServiceProviderType type;
	private String website;
	private String iconUrl;
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ServiceProviderType getType() {
		return type;
	}

	public void setType(ServiceProviderType type) {
		this.type = type;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public static ApiServiceProvider from(ServiceProvider from) {
		ApiServiceProvider to = new ApiServiceProvider();
		BeanUtils.copyProperties(from, to);
		return to;
	}
}
