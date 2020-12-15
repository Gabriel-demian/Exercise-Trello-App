package com.proy.excercise.controller;

import java.net.http.HttpResponse;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private Environment env;

	@SuppressWarnings("unchecked")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HttpResponse<String> createTask(
			@RequestParam String key,
			@RequestParam String token,
			@RequestParam String idList,
			@RequestParam String name,
			@RequestParam (required = false) String desc,
			@RequestParam (required = false) String due) {
		
		String urlTrello = env.getProperty("trelloCardUrl");
		
		//HttpRequestWithBody response = null;
		try {
			
			RestTemplate rt = new RestTemplate();
			
			
			
			
			rt.postForLocation(urlTrello+"?key="+key+"&token="+token+"&idList="+idList+"&name="+name+"&desc="+desc+"&due="+due
					, new Object());
			
//			response =  Unirest.post(url)
//					  .queryString("key", key)
//					  .queryString("token", token)
//					  .queryString("idList", idList)
//					  
//					  .queryString("name", name)
//					  .queryString("desc", desc)
//					  .queryString("due", due)
//					  .asString();

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
