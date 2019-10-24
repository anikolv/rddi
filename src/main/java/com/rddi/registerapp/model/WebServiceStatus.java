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

@Entity
@Table(name = "web_services_availability")
public class WebServiceStatus {

	@Id
	@GeneratedValue(generator = "web_services_availability_id_seq")
	@GenericGenerator(name = "web_services_availability_id_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "web_services_availability_id_seq"))
	private Long id;

	@Column(name = "http_status_code")
	private String httpStatusCode;

	@Column(name = "http_status_message")
	private String httpStatusMessage;

	@Column(name = "available")
	private Boolean available;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "web_service_id")
	private WebService webService;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@Column(name = "checked_at")
	private Date checkedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpStatusMessage() {
		return httpStatusMessage;
	}

	public void setHttpStatusMessage(String httpStatusMessage) {
		this.httpStatusMessage = httpStatusMessage;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public Date getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(Date checkedAt) {
		this.checkedAt = checkedAt;
	}

}
