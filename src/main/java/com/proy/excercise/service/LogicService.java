package com.proy.excercise.service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LogicService {
	
	@Autowired
	private Environment env;
	
	public String getNumString() {
		
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
        	
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
            
        }
        String saltStr = salt.toString();
        
        return saltStr;

    }
	
	public String getWordString() {
		
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
        	
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
            
        }
        String saltStr = salt.toString();
        
        return saltStr;

    }
	
	/**
	 * Generates the URL for the get method (to get the members)
	 */
	public String getMembersUrl() {
		
		String key = env.getProperty("trello.key");
		String token = env.getProperty("trello.token");
		String boardId = env.getProperty("trello.board.id");
		String urlTrelloBoard = env.getProperty("trello.board.url");
		
		String url = urlTrelloBoard+"/"+boardId+"/members?key="+key+"&token="+token;
		
		return url;
	}
	
	public int getRandomNumber(int min, int max) {
		
		int randomNum = ThreadLocalRandom.current().nextInt(min, max);

		return randomNum;
	}
	
}
