package com.proy.excercise.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LogicService {


    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    /**
     * Creating a new Random object each time a random value is needed is inefficient
     * and may produce numbers which are not random depending on the JDK.
     */
    private Random rnd = SecureRandom.getInstanceStrong(); // SecureRandom is preferred to Random

    public LogicService() throws NoSuchAlgorithmException {
        // this code is necessary for the SecureRandom.getInstanceStrong() method.
    }

	public String getNumString() {
        String NUM_CHARS = "1234567890";
        return getString(NUM_CHARS);
    }
	
	public String getWordString() {
        String ALPHABET_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getString(ALPHABET_CHARS);
    }

    private String getString(String CHARS) {
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 5) { // length of the random string.
            int index = rnd.nextInt() * CHARS.length();
            salt.append(CHARS.charAt(index));
        }
        return salt.toString();
    }

    /**
	 * Generates the URL for the get method (to get the members)
	 */
	public String getMembersUrl() {
		
		String key = env.getProperty("trello.key");
		String token = env.getProperty("trello.token");
		String boardId = env.getProperty("trello.board.id");
		String urlTrelloBoard = env.getProperty("trello.board.url");
		
		return urlTrelloBoard+"/"+boardId+"/members?key="+key+"&token="+token;
	}
	
	public int getRandomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
}
