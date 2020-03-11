package com.masters.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masters.conf.MasterConf;
import com.masters.conf.Messages;
import com.masters.model.Subject;
import com.masters.repository.SubjectRepository;
import com.masters.request.dto.SubjectReqDto;
import com.masters.response.dto.AppResponse;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	MasterConf masterConf;
	
	@Autowired
	Messages message;
	
	public AppResponse addSubject(SubjectReqDto subjectReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Subject subject = masterConf.getObjectMapper().convertValue(subjectReq, Subject.class);
		subject = subjectRepo.save(subject);
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setMessage(message.get("subject.register"));
		return appResponse;
	}
	
	public AppResponse updateSubject(SubjectReqDto subjectReq) {
		AppResponse appResponse = masterConf.getAppResponse();
		Subject subject = masterConf.getObjectMapper().convertValue(subjectReq, Subject.class);
		subject = subjectRepo.save(subject);
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setMessage(message.get("subject.update"));
		return appResponse;
	}
	
	public AppResponse getSubject(long id) {
		AppResponse appResponse = masterConf.getAppResponse();
		Subject subject = subjectRepo.findById(id);
		appResponse.setStatus(true);
		appResponse.setStatusCode(200);
		appResponse.setData(subject);
		appResponse.setMessage(message.get("subject.get"));
		return appResponse;
	}

}
