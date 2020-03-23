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

import com.masters.request.dto.StafReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.service.StafService;

@RestController
@RequestMapping(value = "/staf")
public class StafCtrl {
	
	@Autowired
	StafService stafService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addStaf(@RequestBody @Valid StafReqDto stafReq , BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			return new ResponseEntity<AppResponse>(stafService.getErrors(bindResult),HttpStatus.OK);
		}
		return new ResponseEntity<AppResponse>(stafService.addStaf(stafReq) , HttpStatus.OK);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateStaf(@RequestBody @Valid StafReqDto stafReq , BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			return new ResponseEntity<AppResponse>(stafService.getErrors(bindResult),HttpStatus.OK);
		}
		return new ResponseEntity<AppResponse>(stafService.updateStaf(stafReq) , HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> getStaf(@PathVariable("id") long id) {
		return new ResponseEntity<AppResponse>(stafService.getStaf(id) , HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllStaf() {
		return new ResponseEntity<AppResponse>(stafService.getAllStaf() , HttpStatus.OK);
	}
}
