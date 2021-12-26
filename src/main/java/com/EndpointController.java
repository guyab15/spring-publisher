package com;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/publisher")
public class EndpointController {
	  @Autowired
	    private QueueSender queueSender;

	    @PostMapping
	    public ResponseEntity<?> send(@RequestBody Customer customer){
	    	ObjectMapper mapper = new ObjectMapper();
	    	System.out.println("post value: "+customer);
	        try {
	        	String json = mapper.writeValueAsString(customer);
				queueSender.send(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().build();
			}catch (AmqpException e) {
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
			}
	        return ResponseEntity.ok("ok. done");
	    }
}
