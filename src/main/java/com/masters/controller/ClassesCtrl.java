package com.masters.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addClasses(@RequestBody @Valid ClassReqDto classReq , BindingResult bindResult){
		if(bindResult.hasErrors()) {
			return new ResponseEntity<AppResponse>(classService.getErrors(bindResult),HttpStatus.OK);
		}
		return new ResponseEntity<AppResponse>(classService.addClasses(classReq),HttpStatus.OK);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateClasses(@RequestBody @Valid ClassReqDto classReq , BindingResult bindResult){
		if(bindResult.hasErrors()) {
			return new ResponseEntity<AppResponse>(classService.getErrors(bindResult),HttpStatus.OK);
		}
		return new ResponseEntity<AppResponse>(classService.updateClasses(classReq),HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> getClass(@PathVariable("id")long id){
		return new ResponseEntity<AppResponse>(classService.getClasses(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllClass(){
		return new ResponseEntity<AppResponse>(classService.getAllClasses(),HttpStatus.OK);
	}
	

}
