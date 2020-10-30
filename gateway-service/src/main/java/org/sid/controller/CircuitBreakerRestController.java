package org.sid.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerRestController {
	
	@GetMapping("/defaultCountries")
	public Map<String,String> countries(){
		Map<String,String> data = new HashMap<>();
		data.put("message", "default countries");
		data.put("countries", "Spain, Maroc, Lebanon...........");
		return data;
		
		
	}

}
