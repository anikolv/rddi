package com.rddi.registerapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.rddi.registerapp.model.enums.ServiceProviderType;

import lombok.Data;

@Entity
@Table(name = "service_providers")
@Data
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
	
}
