package com.masters.request.dto;

import javax.validation.constraints.NotEmpty;

public class SubjectReqDto {
	
	private long id;
	
	@NotEmpty(message = "{subject.name.error}")
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
