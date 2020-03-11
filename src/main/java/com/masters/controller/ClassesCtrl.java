package com.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masters.request.dto.ClassReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.service.ClassService;

@RestController
@RequestMapping(value = "/classes")
public class ClassesCtrl {
	
	@Autowired
	ClassService classService;
	
	@RequestMapping(value = "/add")
	public ResponseEntity<?> addClasses(ClassReqDto classReq){
		return new ResponseEntity<AppResponse>(classService.addClasses(classReq),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update")
	public ResponseEntity<?> updateClasses(ClassReqDto classReq){
		return new ResponseEntity<AppResponse>(classService.updateClasses(classReq),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<?> getClass(long id){
		return new ResponseEntity<AppResponse>(classService.getClasses(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll")
	public ResponseEntity<?> getAllClass(){
		return new ResponseEntity<AppResponse>(classService.getAllClasses(),HttpStatus.OK);
	}
	

}
