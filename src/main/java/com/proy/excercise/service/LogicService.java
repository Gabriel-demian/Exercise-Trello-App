package com.proy.excercise.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class LogicService {
	
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
}
