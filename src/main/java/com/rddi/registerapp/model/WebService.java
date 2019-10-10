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

import lombok.Data;

@Data @Entity
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
	private Long version;
	
	@Column(name = "openapi_contract")
	private String openApiContract;
	
	@Column(name = "documentation_url")
	private String documentationUrl;
	
	@Enumerated(EnumType.STRING)
	private WebServiceType type;
	
	@Column(name = "http_status_code")
	private String httpStatusCode;
	
	@Column(name = "http_status_message")
	private String httpStatusMessage;
	
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

}
