package com.springboot.consumeapi.DAOclass;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public interface DAOClassImp {

	public void findAll() throws JSONException;
	public void  findById(String empId) throws JSONException;
	public String update(Map<String, Object> theEmployee) throws JSONException;
	public String delete(String id) throws JSONException;
}
