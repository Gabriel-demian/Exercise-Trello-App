package com.proy.excercise.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;


public class TrelloCard {
	
	@NotNull
	private String key;
	@NotNull
	private String token;
	@NotNull
	private String idList;
	
	
	private String name;
	private String desc;
	private Date due;
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

	
	
}
