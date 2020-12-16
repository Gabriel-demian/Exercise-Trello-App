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

import com.proy.excercise.LogicService;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private LogicService logic;


//	@SuppressWarnings("unchecked")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HttpStatus createTask(
			@RequestParam (required = false)String key,
			@RequestParam (required = false)String token,
			@RequestParam (required = false)String idList,
			@RequestParam String title,
			@RequestParam String type,
			@RequestParam (required = false) String description,
			@RequestParam (required = false) String category,
			@RequestParam (required = false) String due) {
		
		//USABLES: type, title, description, category
		
		String urlTrello = env.getProperty("trelloCardUrl");
		key = env.getProperty("TrelloKey");
		token = env.getProperty("TrelloToken");
		
		String issue = "issue";
		String bug = "bug";
		String task ="task";
		
		String mant = "Maintenance";
		String res = "Research";
		String test = "Test";
		
		
		String url = urlTrello+"?key=" + key + "&token=" + token;
		
		
		/**
		 * In case of a task, the card will be assigned to the list depending on the category.
		 * If the category doesn't match it will return 404 Not Found.
		 */
		if(task.equalsIgnoreCase(type)) {
			if(mant.equalsIgnoreCase(category)) {
				
				idList = env.getProperty("MaintenanceListId");
				
			}else if(res.equalsIgnoreCase(category)) {
				
				idList = env.getProperty("ResearchListId");
				
			}else if(test.equalsIgnoreCase(category)) {
				
				idList = env.getProperty("TestListId");
				
			}else {
				return HttpStatus.NOT_FOUND;
			}
		}
			
		if(bug.equalsIgnoreCase(type)) {
			title.concat("-"+logic.getWordString()+"-"+logic.getNumString());
			title.concat("&idLabels=" + env.getProperty("BugLabelId"));
			idList = env.getProperty("ToDoListId");
			url.concat("&idList=" + idList);
		}
		
		if(issue.equalsIgnoreCase(type)) {
			
			url = url.concat("&idList=" + env.getProperty("ToDoListId") + "&name=" + title);
			
		}
		
		
		
		
		try {
			
			RestTemplate rt = new RestTemplate();
			
			//String url = urlTrello+"?key=" + key + "&token=" + token + "&idList=" + idList + "&name=" + title + "&desc="+ description + "&due=" + due;
			
			rt.postForLocation(url, null);
			
		} catch (RestClientException e) {
			e.printStackTrace();
			System.out.println("******************************************"+url);
		}
		
		System.out.println("******************************************"+url);
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
