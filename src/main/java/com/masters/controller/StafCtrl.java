package com.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value = "/add")
	public ResponseEntity<?> addStaf(@RequestBody StafReqDto stafReq) {
		return new ResponseEntity<AppResponse>(stafService.addStaf(stafReq) , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update")
	public ResponseEntity<?> updateStaf(@RequestBody StafReqDto stafReq) {
		return new ResponseEntity<AppResponse>(stafService.updateStaf(stafReq) , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}")
	public void getStaf() {
		
	}
	
	@RequestMapping(value = "/getAll")
	public void getAllStaf() {
		
	}
}
