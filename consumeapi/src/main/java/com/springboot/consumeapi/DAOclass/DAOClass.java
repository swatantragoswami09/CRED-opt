package com.springboot.consumeapi.DAOclass;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

//import com.springboot.consumeapi.Util.JsonUtil;
//import com.springboot.consumeapi.bean.Employee;

@Repository
public class DAOClass implements DAOClassImp {

	@Override
	public void findAll() throws JSONException {
		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.GET,url);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);
		
		String rawJson= response.getBody();//
		System.out.println(rawJson);
		JSONObject root = new JSONObject(rawJson);
//		System.out.println(root.toString());
		JSONArray e=root.getJSONArray("result");
////		System.out.println(e);
		for(int i=0;i<e.length();i++)
		{
			JSONObject json= e.getJSONObject(i);
//			String fr=(String) json.get("number");
			System.out.println("number: "+(String) json.get("number")+", "+"task_effective_number: "+(String) json.get("task_effective_number"));
		}
//		return "";
//		Employee emp1=JsonUtil.convertJsonToJava(rawJson , Employee.class);
//		System.out.println(emp1);

	}

	
	public void parseObject(JSONObject root, String key) throws JSONException {
		System.out.println(root.has(key));
		System.out.println(root.get(key));
	}
	
	
	void getKey(JSONObject root,String empId) throws JSONException {
	
		boolean exists= root.has(empId);
		Iterator<?> keys;
		String nextKeys;
		if(!exists) {
			keys=root.keys();
//			System.out.println(keys);
			while(keys.hasNext()) {
				nextKeys=(String)keys.next();
				try {
					if(root.get(nextKeys) instanceof JSONObject) {
						if(exists == false) {
							getKey(root.getJSONObject(nextKeys),empId);
						}
					}else if(root.get(nextKeys) instanceof JSONArray){
						JSONArray jsonarray = root.getJSONArray(nextKeys);
						for(int i=0;i<jsonarray.length();i++) {
							String jsonarrayString=jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);
							
							if(exists== false ) {
								getKey(innerJSOn,empId);							}
						}
					}
				}
				catch(Exception e) {
					
				}
			}
			
			
		
		}else {
			parseObject(root,empId);
		}
	}
	
	@Override
	public void findById(String empId) throws JSONException{

		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem/"+empId);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);

		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.GET,url);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);
		
		
		String rawJson = response.getBody();  //return JSON string
		
		JSONObject root = new JSONObject(rawJson);
		getKey(root,empId);
		
//		JSONArray result = root.getJSONArray("result");// result value that is list
		
	}

	@Override
	public String update(Map<String, Object> theEmployee) throws JSONException {
		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);
//		System.out.println(base64encstring);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.POST,url);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);

		String rawJson = response.getBody();  //return JSON string
		System.out.println(rawJson);
//		JSONObject root = new JSONObject(rawJson);
		
//		JSONArray list=root.getJSONArray("result");

//		list.put("Test", theEmployee);
//		list.put(theEmployee);
//		System.out.println(list);
//		for(int i=0;i<list.length();i++) {
//			
//		}
//		
//		JSONArray result = root.getJSONArray("result");// result value that is list
//		System.out.println(result);
		
		return "fine";
	}

	@Override
	public String delete(String id) throws JSONException {
		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);
//		System.out.println(base64encstring);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.DELETE,url);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);

		String rawJson = response.getBody();  //return JSON string
		
		JSONObject root = new JSONObject(rawJson);
		
		//		logic
		System.out.println("Before delete:"+root);
		root.remove(id);
		System.out.println("After delete:"+root);


		return "deleted";

	}

}
