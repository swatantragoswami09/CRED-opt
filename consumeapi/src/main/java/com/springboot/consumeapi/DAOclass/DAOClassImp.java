package com.springboot.consumeapi.DAOclass;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public interface DAOClassImp {

	public String findAll() throws JSONException ;
	public void  findById(String empId) throws JSONException;
	public String insert(Map<String, Object> theEmployee) throws JSONException;
	public String update(Map<String, Object> theEmployee,String id) throws JSONException;
	public String delete(String id) throws JSONException;
}
