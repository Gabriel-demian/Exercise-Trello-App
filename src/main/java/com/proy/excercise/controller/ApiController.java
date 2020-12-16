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

import com.proy.excercise.pojo.Member;
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
	public HttpStatus createCard(
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
		 * getAllMembersId will bring an array of members and will call the method getRandomNumber to get a random number to assign the member to the bug. 
		 */
		if(bug.equalsIgnoreCase(type)) {
			
			title="";
			
			Member[] objects = getAllMembers();
			int random = logic.getRandomNumber(0, objects.length-1);
			String member = objects[random].getId();
			
			System.out.println("The magic number is "+ random + " and the member is the id: " + objects[random].getId());
			
			if(!(description.isBlank())) {
				
				title = title.concat("Bug-"+logic.getWordString()+"-"+logic.getNumString());
				
				url = url.concat("&idList=" + env.getProperty("trello.toDo.list.id") 
							+ "&name=" + title + "&idLabels=" + env.getProperty("trello.bug.label.id") + "&idMembers=" + member);
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
				String idLabel = "";
				
				if(mant.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.maintenance.list.id");
					idLabel = env.getProperty("trello.maintenance.label.id");
					
				}else if(res.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.research.list.id");
					idLabel = env.getProperty("trello.research.label.id");
					
				}else if(test.equalsIgnoreCase(category)) {
					
					idList = env.getProperty("trello.test.list.id");
					idLabel = env.getProperty("trello.test.label.id");
					
				}else {
					System.out.println("****Category not acepted******* "+url);
					return HttpStatus.NOT_FOUND;
				}
				
				url = url.concat("&idList=" + idList + "&name=" + title + "&idLabels=" + idLabel);
				
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
	
	
	
	/**
	 * This method is called when we create a new bug. To get the members to be randomly selected.
	 * @return Member[]
	 */
	@GetMapping
	public Member[] getAllMembers() {
		
		/**
		 * logic.getUrlName() generates the URL for the get method (to get the members)
		 */
		String url = logic.getMembersUrl();
		
		Member[] objects = null;
		
		try {
			
			RestTemplate rt = new RestTemplate();
			
			ResponseEntity<Member[]> responseEntity = rt.getForEntity(url, Member[].class);
			
			objects = responseEntity.getBody();
			
		} catch (RestClientException e) {
			System.out.println("****Catch-Get-ID-URL******* "+url);
			e.printStackTrace();
		}
		
		System.out.println("****Ok-Get-ID-URL******* "+url);
		return objects;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
