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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

@Entity
@Table(name = "web_services")
public class WebService {

	@Id
	@GeneratedValue(generator = "web_services_id_generator")
	@GenericGenerator(name = "web_services_id_generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "web_services_id_seq"))
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	private WebServiceCategory category;

	@Column(name = "version")
	private String version;

	@Column(name = "openapi_contract_url")
	private String openApiContract;

	@Column(name = "documentation_url")
	private String documentationUrl;

	@Enumerated(EnumType.STRING)
	private WebServiceType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_web_service_id")
	private WebService parentWebService;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_provider_id")
	private ServiceProvider serviceProvider;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "created_at")
	private Date createdAt;

	@JsonManagedReference
	@OneToMany(mappedBy = "webService", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<WebServiceComment> comments = new LinkedHashSet<WebServiceComment>();

	@JsonManagedReference
	@OneToMany(mappedBy = "webService", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<WebServiceStatus> httpStatuses = new LinkedHashSet<WebServiceStatus>();

	@JsonManagedReference
	@OneToMany(mappedBy = "webService", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<WebServiceRating> ratings = new LinkedHashSet<WebServiceRating>();

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WebServiceCategory getCategory() {
		return category;
	}

	public void setCategory(WebServiceCategory category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOpenApiContract() {
		return openApiContract;
	}

	public void setOpenApiContract(String openApiContract) {
		this.openApiContract = openApiContract;
	}

	public String getDocumentationUrl() {
		return documentationUrl;
	}

	public void setDocumentationUrl(String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}

	public WebServiceType getType() {
		return type;
	}

	public void setType(WebServiceType type) {
		this.type = type;
	}

	public WebService getParentWebService() {
		return parentWebService;
	}

	public void setParentWebService(WebService parentWebService) {
		this.parentWebService = parentWebService;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<WebServiceComment> getComments() {
		return comments;
	}

	public void setComments(Set<WebServiceComment> comments) {
		this.comments = comments;
	}

	public void addServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
		serviceProvider.getWebServices().add(this);
	}

	public Set<WebServiceStatus> getHttpStatuses() {
		return httpStatuses;
	}

	public void setHttpStatuses(Set<WebServiceStatus> httpStatuses) {
		this.httpStatuses = httpStatuses;
	}

	public void addStatus(WebServiceStatus webServiceStatus) {
		this.httpStatuses.add(webServiceStatus);
		webServiceStatus.setWebService(this);
	}

	public Set<WebServiceRating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<WebServiceRating> ratings) {
		this.ratings = ratings;
	}

}
