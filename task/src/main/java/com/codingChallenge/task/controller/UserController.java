package com.codingChallenge.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codingChallenge.task.model.User;
import com.codingChallenge.task.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @GetMapping("/customer/hello")
    public String customerHello() {
        return "Hello, Customer!";
    }

    
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello, Admin!";
    }
    
    @PostMapping("/auth/signup")
    public void signup(@RequestBody User user) {
    	user.setRole("ROLE_CUSTOMER");
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	userRepository.save(user);
    }
}
