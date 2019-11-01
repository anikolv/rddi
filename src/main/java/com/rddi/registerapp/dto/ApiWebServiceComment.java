package com.rddi.registerapp.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rddi.registerapp.model.WebServiceComment;

@JsonInclude(Include.NON_EMPTY)
public class ApiWebServiceComment {

	private Long id;
	private String author;
	private String comment;
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public static ApiWebServiceComment from(WebServiceComment from) {
		ApiWebServiceComment to = new ApiWebServiceComment();
		BeanUtils.copyProperties(from, to);
		return to;
	}

}
