package com.masters.response.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.masters.model.Subject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassResDto {
	
	private long id;
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
