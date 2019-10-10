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

@Data @Entity
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
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "created_at")
	private Date createdAt;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "serviceProvider", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<WebService> webServices = new LinkedHashSet<WebService>();
	
}
