package com.springboot.consumeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//import com.springboot.consumeapi.Util.JsonUtil;
//import com.springboot.consumeapi.bean.Employee;

@SpringBootApplication
public class ConsumeapiApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(ConsumeapiApplication.class, args);
		
//		Employee emp= new Employee();
//		emp.setEmpNo(201);;
//		emp.setName("Rama");
//		emp.setSalary(2000000);
//		String jsonEmployee= JsonUtil.convertJavaToJson(emp);
//		System.out.println(jsonEmployee);
//		System.out.println("------------------------------------");
//		Employee emp1=JsonUtil.convertJsonToJava(jsonEmployee , Employee.class);
//		System.out.println(emp1.getEmpNo()+" "+emp1.getName()+" "+emp1.getSalary());
		
		
	}

}
