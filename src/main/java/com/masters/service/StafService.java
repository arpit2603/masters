package com.masters.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Staf;
import com.masters.repository.StafRepository;
import com.masters.request.dto.StafReqDto;
import com.masters.response.dto.AppResponse;

@Service
public class StafService {
	
	@Autowired
	StafRepository stafRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	public AppResponse addStaf(StafReqDto stafReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Staf staf = masterConf.getObjectMapper().convertValue(stafReq, Staf.class);
		staf = stafRepo.save(staf);
		if(staf != null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("staf.register"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("staf.register.error"));
			appResponse.setStatusCode(500);
		}
		return appResponse;
	}
	
	public AppResponse updateStaf(StafReqDto stafReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Staf staf = masterConf.getObjectMapper().convertValue(stafReq, Staf.class);
		staf = stafRepo.save(staf);
		if(staf != null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("staf.update"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("staf.update.error"));
			appResponse.setStatusCode(500);
		}
		return appResponse;
	}
	
	public AppResponse getStaf(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		Staf staf = stafRepo.findById(id);
		if(staf == null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("staf.get"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("staf.get.error"));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse getAllStaf() {
		AppResponse appResponse = masterConf.getAppResponse();
		Iterable<Staf> stafList =  stafRepo.findAll();
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setData(stafList);
		appResponse.setMessage(message.get("staf.get.all"));
		return appResponse;
		
	}
}

