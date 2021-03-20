package com.springboot.consumeapi.service;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;

//import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.springboot.consumeapi.DAOclass.DAOClass;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceClass implements services {

	@Autowired
	private DAOClass daoclass;
//	
//	public ServiceClass(DAOClass daoclass) {
//		super();
//		this.daoclass = daoclass;
//	}

	@Override
	public String findAll() throws JSONException {
		 daoclass.findAll();
		 return "Service called";

	}

	@Override
	public String findById(String empId) throws JSONException {
		 daoclass.findById(empId);
		 return "Service called by unique ";
	}

	@Override
//	@Transactional
	public String update(Map<String, Object> theEmployee) throws JSONException {

		return  daoclass.update(theEmployee);
//		 return "Update method called";
		 
	}

	@Override
	public String delete(String id) throws JSONException {
		return daoclass.delete(id);
		
	}

}
