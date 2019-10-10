package com.rddi.registerapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data @Entity
@Table(name = "web_services_comments")
public class WebServiceComment {
	
	@Id
	@GeneratedValue(generator = "web_services_comments_id_generator")
	@GenericGenerator(name = "web_services_comments_id_generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "web_services_comments_id_seq"))
	private Long id;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "web_service_id")
	private WebService webService;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "created_at")
	private Date createdAt;

}
