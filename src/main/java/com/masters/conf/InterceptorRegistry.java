package com.masters.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorRegistry extends WebMvcConfigurerAdapter {
	
	@Autowired
	TokenExpireIntercepter masterInterceptor;
	
	@Override
	public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
		registry.addInterceptor(masterInterceptor);
	}

}
