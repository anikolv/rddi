package com.rddi.registerapp.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rddi.registerapp.model.enums.ServiceProviderType;

import lombok.Data;

@Entity
@Table(name = "service_providers")
public class ServiceProvider {
	
	@Id
	@GeneratedValue(generator = "service_providers_id_generator")
	@GenericGenerator(name = "service_providers_id_generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "service_providers_id_seq"))
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private ServiceProviderType type;
	
	@Column(name = "parent_business_organization")
	private String parentBusinessOrganization;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "icon_url")
	private String iconUrl;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "created_at")
	private Date createdAt;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "serviceProvider", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<WebService> webServices = new LinkedHashSet<WebService>();

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

	public String getParentBusinessOrganization() {
		return parentBusinessOrganization;
	}

	public void setParentBusinessOrganization(String parentBusinessOrganization) {
		this.parentBusinessOrganization = parentBusinessOrganization;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<WebService> getWebServices() {
		return webServices;
	}

	public void setWebServices(Set<WebService> webServices) {
		this.webServices = webServices;
	}	
}
