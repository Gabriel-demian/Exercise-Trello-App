package com.proy.excercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private Environment env;


//	@SuppressWarnings("unchecked")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HttpStatus createTask(
			@RequestParam String key,
			@RequestParam String token,
			@RequestParam String idList,
			@RequestParam String name,
			@RequestParam (required = false) String desc,
			@RequestParam (required = false) String due) {
		
		String urlTrello = env.getProperty("trelloCardUrl");
		
		try {
			
			RestTemplate rt = new RestTemplate();
			
			String url = urlTrello+"?key="+key+"&token="+token+"&idList="+idList+"&name="+name+"&desc="+desc+"&due="+due;
			
			rt.postForLocation(url, null);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		return HttpStatus.OK;
	}
	
	
	
	@GetMapping
	public Object[] getAllMembers(
			@RequestParam String key,
			@RequestParam String token,
			@RequestParam String boardId) {
		
		String urlTrelloBoard = env.getProperty("trelloBoardUrl");
		
		Object[] objects = null;
		try {
			
			RestTemplate rt = new RestTemplate();
			
			ResponseEntity<Object[]> responseEntity = rt.getForEntity(urlTrelloBoard+"/"+boardId+"/members?key="+key+"&token="+token, Object[].class);
			objects = responseEntity.getBody();
			
			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		
		return objects;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
