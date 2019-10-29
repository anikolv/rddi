package com.rddi.registerapp.dto;

public class CommentWebServiceRequest {

	private Long webServiceId;
	private String author;
	private String comment;

	public Long getWebServiceId() {
		return webServiceId;
	}

	public void setWebServiceId(Long webServiceId) {
		this.webServiceId = webServiceId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
