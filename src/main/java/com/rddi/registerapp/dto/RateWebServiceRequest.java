package com.rddi.registerapp.dto;

public class RateWebServiceRequest {

	private Long webServiceId;
	private Long rating;

	public Long getWebServiceId() {
		return webServiceId;
	}

	public void setWebServiceId(Long webServiceId) {
		this.webServiceId = webServiceId;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

}
