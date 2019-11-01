package com.rddi.registerapp.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceComment;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.model.enums.WebServiceCategory;
import com.rddi.registerapp.model.enums.WebServiceType;

@JsonInclude(Include.NON_EMPTY)
public class ApiWebServiceDetails {

	private Long id;
	private String name;
	private String shortDescription;
	private String description;
	private WebServiceCategory category;
	private String version;
	private String openApiContractUrl;
	private String documentationUrl;
	private WebServiceType type;
	private Date createdAt;
	private ApiServiceProvider serviceProvider;
	private ApiWebServiceEvaluation evaluationDetails;
	private List<ApiWebServiceComment> comments = new ArrayList<>();

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

	public String getOpenApiContractUrl() {
		return openApiContractUrl;
	}

	public void setOpenApiContractUrl(String openApiContractUrl) {
		this.openApiContractUrl = openApiContractUrl;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ApiServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ApiServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public ApiWebServiceEvaluation getEvaluationDetails() {
		return evaluationDetails;
	}

	public void setEvaluationDetails(ApiWebServiceEvaluation evaluationDetails) {
		this.evaluationDetails = evaluationDetails;
	}

	public List<ApiWebServiceComment> getComments() {
		return comments;
	}

	public void setComments(List<ApiWebServiceComment> comments) {
		this.comments = comments;
	}
	
	public void addComment(ApiWebServiceComment comment) {
		this.comments.add(comment);
	}
	
	public static ApiWebServiceDetails from(WebService from) {
		ApiWebServiceDetails to = new ApiWebServiceDetails();
		
		BeanUtils.copyProperties(from, to, "serviceProvider", "comments");
		to.setServiceProvider(ApiServiceProvider.from(from.getServiceProvider()));
		
		from.getComments()
		.stream()
		.sorted(Comparator.comparingLong(WebServiceComment::getId).reversed())
		.forEach(comment -> to.addComment(ApiWebServiceComment.from(comment)));
		
		return to;
	}
	
	public void addEvaluationDetails(WebServiceStatus webServiceStatus, Double lastMonthAvailabilityInPercentage,
			Double reliabilityPercentage, Double averageRating) {
		ApiWebServiceEvaluation to = new ApiWebServiceEvaluation();
		to.setAverageRating(averageRating.intValue());
		to.setLastMonthAvailabilityInPercentage(lastMonthAvailabilityInPercentage.intValue());
		to.setReliabilityPercentage(reliabilityPercentage.intValue());
		to.setWebServiceStatus(webServiceStatus.getAvailable() ? "ONLINE" : "OFFLINE");
		
		this.setEvaluationDetails(to);
	}

}
