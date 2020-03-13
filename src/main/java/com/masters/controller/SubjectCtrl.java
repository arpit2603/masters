package com.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addSubject(@RequestBody SubjectReqDto subjectReq){
		return new ResponseEntity<AppResponse>(subjectService.addSubject(subjectReq),HttpStatus.OK);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateSubject(@RequestBody SubjectReqDto subjectReq){
		return new ResponseEntity<AppResponse>(subjectService.updateSubject(subjectReq),HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> getSubject(@PathVariable("id")long id){
		return new ResponseEntity<AppResponse>(subjectService.getSubject(id),HttpStatus.OK);
	}

}
