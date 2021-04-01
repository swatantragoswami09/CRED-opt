package com.springboot.consumeapi.DAOclass;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

//import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	public String findAll() throws JSONException {
		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.GET,url);
//		System.out.println(request);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);
		System.out.println(response);
		
		String rawJson= response.getBody();//
//		System.out.println(rawJson);
		JSONObject root = new JSONObject(rawJson);
		System.out.println(root.toString(12));
		JSONArray e=root.getJSONArray("result");
//		System.out.println(e);
//		for(int i=0;i<e.length();i++)
//		{
//			JSONObject json= e.getJSONObject(i);
////			String fr=(String) json.get("number");
//			System.out.println("number: "+(String) json.get("number")+", "+"task_effective_number: "+(String) json.get("task_effective_number"));
//		}
//		return "";
//		Employee emp1=JsonUtil.convertJsonToJava(rawJson , Employee.class);
//		System.out.println(emp1);
		return root.toString(100);

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

		
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);

		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.GET,url);
		System.out.println(request);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);
		
		
		String rawJson = response.getBody();  //return JSON string
		
		JSONObject root = new JSONObject(rawJson);
//		getKey(root,empId);
		
		JSONArray e = root.getJSONArray("result");// result value that is list
		
		for(int i=0;i<e.length();i++)
		{
			JSONObject json= e.getJSONObject(i);
//			String fr=(String) json.get("number");
			System.out.println("sys_id: "+(String) json.get(empId));
		}
		
	}

	@Override
	public String insert(Map<String, Object> theEmployee) throws JSONException {
		
		System.out.println(theEmployee);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic "+new String(encode);
//		System.out.println(base64encstring);
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
//		System.out.println(header);
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.POST,url);
//		System.out.println(request);
		ResponseEntity<String> response = restTemplate.postForEntity(url,request,  String.class);
//		System.out.println(response);
		String rawJson = response.getBody();  //return JSON string
//		System.out.println(rawJson);
		JSONObject root = new JSONObject(rawJson);
//		System.out.println(root.toString(5));
//		JSONArray list=root.getJSONArray("result");

//		 for(int i = 0; i < list.length();i++) {
//	            JSONObject innerObj = list.getJSONObject(i);
//	            for(Iterator it = innerObj.keys(); it.hasNext(); ) {
//	                String key = (String)it.next();
//	                System.out.println(key + ":" + innerObj.get(key));
//	            }
//	        }
		root.put("Test", theEmployee);
//		System.out.println(root.toString(5));
		findAll();
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

		public String update(Map<String, Object> theEmployee,String id) throws JSONException {
		
//		System.out.println(theEmployee);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic "+new String(encode);
//		System.out.println(base64encstring);
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
//		System.out.println(header);
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.POST,url);
//		System.out.println(request);
		ResponseEntity<String> response = restTemplate.postForEntity(url,request,  String.class);
//		System.out.println(response);
		String rawJson = response.getBody();  //return JSON string
//		System.out.println(rawJson);
		JSONObject root = new JSONObject(rawJson);
		
		JSONArray e = root.getJSONArray("result");// result value that is list
		
		for(int i=0;i<e.length();i++)
		{
			JSONObject json= e.getJSONObject(i);
//			String fr=(String) json.get("number");
			if((String) json.get("sys_id")==id)
			json.put("PRB0040026_new", "Final_updates");
		}
		findAll();
		
		return "fine";
	}

	@Override
	public String delete(String id) throws JSONException {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		URI url=URI.create("https://dev59463.service-now.com/api/now/table/problem");
		String usernamePassword= "admin:SSZrUSp5a9cm";
		byte[] encode=Base64Utils.encode(usernamePassword.getBytes());
		String base64encstring="Basic " + new String(encode);
		header.set(HttpHeaders.AUTHORIZATION, base64encstring);
		
		RequestEntity<String> request = new RequestEntity<>(header, HttpMethod.GET,url);
		ResponseEntity<String> response = restTemplate.exchange(request,  String.class);

		String rawJson = response.getBody();  //return JSON string
		
		JSONObject root = new JSONObject(rawJson);
		
		JSONArray list=root.getJSONArray("result");
		
		 for(int i = 0; i < list.length();i++) {
	            JSONObject json = list.getJSONObject(i);
	            if((String) json.get("sys_id")==id)
	                json.remove(id);
	            }
	        
		
		
		//logic
//		System.out.println("Before delete:"+root);
//		root.remove(id);
//		System.out.println("After delete:"+root);


		return "deleted";

	}

}
