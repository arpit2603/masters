package com.masters.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.masters.service.CommonService;
import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Subject;
import com.masters.repository.SubjectRepository;
import com.masters.request.dto.SubjectReqDto;
import com.masters.response.dto.AppResponse;
import com.masters.response.dto.SubjectResDto;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	@Autowired
	CommonService commonService;
	
	public AppResponse addSubject(SubjectReqDto subjectReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!subjectRepo.existsByName(subjectReq.getName())) {
			Subject subject = mapper.convertValue(subjectReq, Subject.class);
			subject = subjectRepo.save(subject);
			appResponse.setStatus(true);
			appResponse.setData(mapper.convertValue(subject, SubjectResDto.class));
			appResponse.setStatusCode(200);
			appResponse.setMessage(message.get("subject.register"));
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("subject.register.exist.error"));
			appResponse.setData(mapper.convertValue(subjectReq, SubjectReqDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse updateSubject(SubjectReqDto subjectReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		if(!subjectRepo.existsByName(subjectReq.getName())) {
			Subject subject = mapper.convertValue(subjectReq, Subject.class);
			subject = subjectRepo.save(subject);
			appResponse.setStatus(true);
			appResponse.setStatusCode(200);
			appResponse.setData(mapper.convertValue(subject, SubjectResDto.class));
			appResponse.setMessage(message.get("subject.update"));
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage(message.get("subject.register.exist.error"));
			appResponse.setData(mapper.convertValue(subjectReq, SubjectReqDto.class));
			appResponse.setStatusCode(200);
		}
		return appResponse;
	}
	
	public AppResponse getSubject(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		ObjectMapper mapper = masterConf.getObjectMapper();
		Subject subject = subjectRepo.findById(id);
		if(subject != null) {
			appResponse.setStatus(true);
			appResponse.setStatusCode(200);
			appResponse.setData(mapper.convertValue(subject, SubjectResDto.class));
			appResponse.setMessage(message.get("subject.get"));
		}else {
			appResponse.setStatus(true);
			appResponse.setStatusCode(200);
			appResponse.setMessage(message.get("subject.get.error"));
		}
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
