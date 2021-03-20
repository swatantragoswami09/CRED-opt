package com.springboot.consumeapi.service;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public interface services {
	public String findAll() throws JSONException;
	public String findById(String id) throws JSONException;
	public String update(Map<String, Object> theEmployee) throws JSONException;
	public String delete(String id) throws JSONException;

	

}
