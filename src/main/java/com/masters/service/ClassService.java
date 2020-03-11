package com.masters.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Classes;
import com.masters.repository.ClassRepository;
import com.masters.request.dto.ClassReqDto;
import com.masters.response.dto.AppResponse;

@Service
public class ClassService {
	
	@Autowired
	ClassRepository classRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	public AppResponse addClasses(ClassReqDto classReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Classes classes = masterConf.getObjectMapper().convertValue(classReq, Classes.class);
		classes = classRepo.save(classes);
		if(classes != null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("class.register"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("class.register.error"));
			appResponse.setStatusCode(500);
		}
		return appResponse;
	}
	
	public AppResponse updateClasses(ClassReqDto classReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Classes classes = masterConf.getObjectMapper().convertValue(classReq, Classes.class);
		classRepo.save(classes);
		appResponse.setStatus(true);
		appResponse.setMessage(message.get("class.register"));
		appResponse.setStatusCode(200);
		return appResponse;
	}
	
	public AppResponse getClasses(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		Classes classes = classRepo.findAllById(id);
		if(classes == null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("class.get"));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("class.get.error"));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse getAllClasses() {
		AppResponse appResponse = masterConf.getAppResponse();
		Iterable<Classes> classesList = classRepo.findAll();
		appResponse.setStatus(true);
		appResponse.setData(classesList);
		appResponse.setStatusCode(200);
		appResponse.setMessage(message.get("class.get.all"));
		return appResponse;
	}

}
