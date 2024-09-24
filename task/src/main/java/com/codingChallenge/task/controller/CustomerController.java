package com.codingChallenge.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingChallenge.task.model.Customer;
import com.codingChallenge.task.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		return ResponseEntity.ok(customerService.addCustomer(customer));
	}
	
	
}
