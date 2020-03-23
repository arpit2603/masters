package com.masters.conf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.masters.response.dto.AppResponse;

@Component
public class TokenExpireIntercepter implements HandlerInterceptor {
	
	@Autowired
	MasterConf masterConf;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AppResponse appResponse = masterConf.getAppResponse();
		String token = request.getHeader("tkn");
		String requestIp = request.getRemoteAddr();
		long currentTime = new Date().getTime();
		URL url = new URL(masterConf.getLognmanagerHost()+"lognmanager/token/expired");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		JSONObject requestJson = new JSONObject();
		requestJson.put("token", token);
		requestJson.put("requestIp", requestIp);
		requestJson.put("requestTime", currentTime);
		OutputStream os = conn.getOutputStream();
		os.write(requestJson.toString().getBytes());
		os.flush();
		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//			throw new RuntimeException("Failed : HTTP error code : "
//				+ conn.getResponseCode());
			appResponse.setStatus(false);
			appResponse.setMessage("Token not authenticated due to api error.");
			response.getWriter().print(appResponse);
			return false;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output;
		while ((output = br.readLine()) != null) {
			output += output;
		}
		conn.disconnect();
		JSONParser parser = new JSONParser();
		JSONObject tokenJson = (JSONObject)parser.parse(output);
		if(tokenJson.containsKey("status") && (boolean)tokenJson.get("status")) {
			return true;
		}else {
			appResponse.setStatus(false);
			appResponse.setMessage((String)tokenJson.get("message"));
			response.getWriter().print(appResponse);
			return false;
		}
	}
}
