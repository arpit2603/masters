package com.masters.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Staf;
import com.masters.repository.StafRepository;
import com.masters.request.dto.StafReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.response.dto.StafResDto;

@Service
public class StafService {
	
	@Autowired
	StafRepository stafRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	@Autowired
	CommonService commonService;
	
	public AppResponse addStaf(StafReqDto stafReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!stafRepo.existsByName(stafReq.getName())) {
			Staf staf = mapper.convertValue(stafReq, Staf.class);
			staf = stafRepo.save(staf);
			if(staf != null && staf.getId() > 0) {
				appResponse.setStatus(true);
				appResponse.setMessage(message.get("staf.register"));
				appResponse.setData(mapper.convertValue(staf, StafResDto.class));
				appResponse.setStatusCode(200);
			}else {
				appResponse.setStatus(false);
				appResponse.setMessage(message.get("staf.register.db.error"));
				appResponse.setStatusCode(500);
			}
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("staf.register.exist.error"));
			appResponse.setData(mapper.convertValue(stafReq, StafResDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse updateStaf(StafReqDto stafReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!stafRepo.existsByName(stafReq.getName())) {
			Staf staf = mapper.convertValue(stafReq, Staf.class);
			staf = stafRepo.save(staf);
			if(staf != null) {
				appResponse.setStatus(true);
				appResponse.setMessage(message.get("staf.update"));
				appResponse.setData(mapper.convertValue(staf, StafResDto.class));
				appResponse.setStatusCode(200);
			}else {
				appResponse.setStatus(false);
				appResponse.setMessage(message.get("staf.update.error"));
				appResponse.setData(mapper.convertValue(stafReq, StafResDto.class));
				appResponse.setStatusCode(500);
			}
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("staf.register.exist.error"));
			appResponse.setData(mapper.convertValue(stafReq, StafResDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse getStaf(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		Staf staf = stafRepo.findById(id);
		if(staf != null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("staf.get"));
			appResponse.setData(mapper.convertValue(staf, StafResDto.class));
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
	
	public AppResponse getErrors(BindingResult bindResult) {
		AppResponse appResponse = masterConf.getAppResponse();
		appResponse.setStatus(false);
		appResponse.setErrors(commonService.getErrors(bindResult));
		appResponse.setMessage(message.get("bind.error.message"));
		return appResponse;
	}
}

