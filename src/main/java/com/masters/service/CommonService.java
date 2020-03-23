package com.masters.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


@Service
public class CommonService {
	
	@SuppressWarnings("unchecked")
	public JSONArray getErrors(BindingResult bindResult) {
		List<FieldError> errorList = bindResult.getFieldErrors();
		JSONArray errors = new JSONArray();
		for(FieldError fieldError : errorList) {
			JSONObject error = new JSONObject();
			error.put("name", fieldError.getField());
			error.put("message", fieldError.getDefaultMessage());
			errors.add(error);
		}
		return errors;
	}

}
