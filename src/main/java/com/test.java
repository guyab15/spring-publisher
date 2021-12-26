package com;

import com.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
public static void main(String[] args) {
	Customer c = new Customer();
	c.setAddress("yad-labnim");
	c.setName("guy");
	c.setFamily("avraham");
	c.setPhoneNumber("050432411");
	c.setPostalCode("1234");
	c.setEmail("guy@gmail.com");
	c.setCity("tel-aviv");
	
	ObjectMapper mapper = new ObjectMapper();
	try {
		String json = mapper.writeValueAsString(c);
		System.out.println(json);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
