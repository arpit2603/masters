package com.masters.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Classes;
import com.masters.repository.ClassRepository;
import com.masters.request.dto.ClassReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.response.dto.ClassResDto;

@Service
public class ClassService {
	
	@Autowired
	ClassRepository classRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	@Autowired
	CommonService commonService;
	
	public AppResponse addClasses(ClassReqDto classReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!classRepo.existsByName(classReq.getName())) {
			Classes classes = mapper.convertValue(classReq, Classes.class);
			classes = classRepo.save(classes);
			if(classes != null) {
				appResponse.setStatus(true);
				appResponse.setMessage(message.get("class.register"));
				appResponse.setData(mapper.convertValue(classes, ClassResDto.class));
				appResponse.setStatusCode(200);
			}else {
				appResponse.setStatus(false);
				appResponse.setMessage(message.get("class.register.error"));
				appResponse.setStatusCode(500);
			}
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("class.register.exist.error"));
			appResponse.setData(mapper.convertValue(classReq, ClassResDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse updateClasses(ClassReqDto classReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!classRepo.existsByName(classReq.getName())) {
			Classes classes = mapper.convertValue(classReq, Classes.class);
			classRepo.save(classes);
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("class.update"));
			appResponse.setData(mapper.convertValue(classes, ClassResDto.class));
			appResponse.setStatusCode(200);
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("class.register.exist.error"));
			appResponse.setData(mapper.convertValue(classReq, ClassResDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse getClasses(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		Classes classes = classRepo.findAllById(id);
		if(classes != null) {
			appResponse.setStatus(true);
			appResponse.setMessage(message.get("class.get"));
			appResponse.setData(mapper.convertValue(classes, ClassResDto.class));
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
	
	public AppResponse getErrors(BindingResult bindResult) {
		AppResponse appResponse = masterConf.getAppResponse();
		appResponse.setStatus(false);
		appResponse.setErrors(commonService.getErrors(bindResult));
		appResponse.setMessage(message.get("bind.error.message"));
		return appResponse;
	}

}
