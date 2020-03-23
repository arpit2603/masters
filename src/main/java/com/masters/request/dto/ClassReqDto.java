package com.masters.request.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.masters.model.Subject;

public class ClassReqDto {
	
	private long id;
	
	@NotEmpty(message = "{class.name.error}")
	private String name;
	
	private List<Subject> subjects;
	
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
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
