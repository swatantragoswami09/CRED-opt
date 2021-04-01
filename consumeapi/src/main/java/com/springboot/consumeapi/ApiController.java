package com.springboot.consumeapi;

//import java.awt.PageAttributes.MediaType;
import java.io.FileReader;
//import static com.jayway.restassured.RestAssured.given;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.Document;
//import javax.transaction.Transactional;

import org.apache.tomcat.util.json.JSONParser;

//import javax.persistence.EntityManager;

import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.luv2code.springboot.cruddemo.entity.Employee;
import com.springboot.consumeapi.service.ServiceClass;

//import io.restassured.RestAssured;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject ;
import javax.ws.rs.core.MediaType;

@Controller
@RestController
//@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ServiceClass serviceclass;
//	private EntityManager entityManager;


	@GetMapping("/employees")
	public String  GetAll() throws JSONException {
		 return serviceclass.findAll().toString();
	}
	
	@GetMapping("/employees/{empId}")
	public String GetById(@PathVariable String empId) throws JSONException {
		 return serviceclass.findById(empId);
			}
	
	@RequestMapping(value="/employees", produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	public @ResponseBody String updateEmployeebyPost(@RequestBody Map<String, Object> theEmployee) throws JSONException {
		
//		return theEmployee.toString();
//		theEmployee.forEach((key,value)->{System.out.println("Header Name:"+key+" Header Value: "+ value);});
		return serviceclass.insert(theEmployee);

		
	}
	
	@RequestMapping(value="/employees/{empId}",produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON}) //update the record
	public String 	updateEmployeebyPut(@PathVariable String empId,@RequestBody Map<String,Object> theemp ) throws JSONException {
//			System.out.println(empId);
		 serviceclass.update(theemp,empId);
			return "Updated";
		}
	
//	add mapping for DELETE /employee/{employeeId}  delete employee
	@DeleteMapping(value="/employees/{sys_id}")
	public String deleteEmployee(@PathVariable String sys_id) throws JSONException {
		
		 serviceclass.delete(sys_id);
		return "Deleted successfully";
		}

	
	
	

}
