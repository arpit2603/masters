package com.masters.conf;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.masters.response.dto.AppResponse;


@Configuration
public class MasterConf {
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	@Scope(scopeName = "prototype")
	public AppResponse getAppResponse() {
		return new AppResponse();
	}

}
