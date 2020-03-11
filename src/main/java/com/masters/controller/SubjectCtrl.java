package com.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masters.request.dto.SubjectReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.service.SubjectService;

@RestController
@RequestMapping(value = "/subject")
public class SubjectCtrl {
	
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping(value = "/add")
	public ResponseEntity<?> addSubject(SubjectReqDto subjectReq){
		return new ResponseEntity<AppResponse>(subjectService.addSubject(subjectReq),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update")
	public ResponseEntity<?> updateSubject(SubjectReqDto subjectReq){
		return new ResponseEntity<AppResponse>(subjectService.updateSubject(subjectReq),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<?> getSubject(long id){
		return new ResponseEntity<AppResponse>(subjectService.getSubject(id),HttpStatus.OK);
	}

}
