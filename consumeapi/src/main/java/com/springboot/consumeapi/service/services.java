package com.springboot.consumeapi.service;

import org.json.JSONException;

public interface services {
	public String findAll() throws JSONException;
	public void findById(String id) throws JSONException;
	public void update(String theEmployee) throws JSONException;
	public String delete(String id) throws JSONException;

	

}
