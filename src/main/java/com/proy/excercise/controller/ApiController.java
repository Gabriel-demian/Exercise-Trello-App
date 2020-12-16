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

import com.proy.excercise.service.LogicService;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private LogicService logic;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HttpStatus createTask(
			@RequestParam (required = false) String title,
			@RequestParam (required = false) String type,
			@RequestParam (required = false) String description,
			@RequestParam (required = false) String category){
		
		String urlTrello = env.getProperty("trello.card.url");
		String key = env.getProperty("trello.key");
		String token = env.getProperty("trello.token");
		
		String issue = "issue";
		String bug = "bug";
		String task ="task";
		String mant = "Maintenance";
		String res = "Research";
		String test = "Test";
		
		String url = urlTrello+"?key=" + key + "&token=" + token;
		
		
		
		/**
		 * An issue must have a short title and a description. All issues gets added to the “To Do” list as unassigned
		 */
		if(issue.equalsIgnoreCase(type)) {
			if(!(title.isBlank() || description.isBlank())) {
				
				url = url.concat("&idList=" + env.getProperty("trello.toDo.list.id") + "&name=" + title + "&desc=" + description);
				
			}else {
				System.out.println("****Catch-Issue-URL******* "+url);
				return HttpStatus.BAD_REQUEST;
			}
			
		}
		
		/**
		 * The title needs to be randomized with the following pattern: bug-{word}-{number} and have the “Bug” label.
		 */
		if(bug.equalsIgnoreCase(type)) {
			
			title="";
			
			if(!(description.isBlank())) {
				
				title = title.concat("Bug-"+logic.getWordString()+"-"+logic.getNumString());
				
				url = url.concat("&idList=" + env.getProperty("trello.toDo.list.id") + "&name=" + title + "&idLabels=" + env.getProperty("trello.bug.label.id"));
			}else {
				System.out.println("****Catch-Bug-URL******* "+url);
				return HttpStatus.BAD_REQUEST;
			}
			
			
		}
		
		
		/**
		 * In case of a task, the card will be assigned to the list depending on the category.
		 * If the category doesn't match it will return 404 Not Found.
		 * A task must have a title.
		 */
		if(task.equalsIgnoreCase(type)) {
			if(!title.isBlank()) {
				
				String idList = "";
				
				if(mant.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.maintenance.list.id");
					
				}else if(res.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.research.list.id");
					
				}else if(test.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.test.list.id");
					
				}else {
					System.out.println("****Category not acepted******* "+url);
					return HttpStatus.NOT_FOUND;
				}
				
				url = url.concat("&idList=" + idList + "&name=" + title );
				
			}else {
				System.out.println("****Catch-Task-URL******* "+url);
				return HttpStatus.BAD_REQUEST;
			}

		}
		
		
		try {
			
			RestTemplate rt = new RestTemplate();
			
			rt.postForLocation(url, null);
			
		} catch (RestClientException e) {
			e.printStackTrace();
			System.out.println("****Catch-URL******* "+url);
		}
		
		System.out.println("****Ok-Return-URL******* "+url);
		return HttpStatus.OK;
	}
	
	
	
	@GetMapping
	public Object[] getAllMembers() {
		
		/**
		 * parameters needed for the members request
		 */
		String key = env.getProperty("trello.key");
		String token = env.getProperty("trello.token");
		String boardId = env.getProperty("trello.board.id");
		String urlTrelloBoard = env.getProperty("trello.board.url");
		
		
		String url = urlTrelloBoard+"/"+boardId+"/members?key="+key+"&token="+token;
		
		Object[] objects = null;
		
		try {
			
			RestTemplate rt = new RestTemplate();
			
			ResponseEntity<Object[]> responseEntity = rt.getForEntity(url, Object[].class);
			
			objects = responseEntity.getBody();
			
		} catch (RestClientException e) {
			System.out.println("****Catch-Get-URL******* "+url);
			e.printStackTrace();
		}
		
		System.out.println("****Ok-Get-URL******* "+url);
		return objects;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
