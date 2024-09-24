package com.codingChallenge.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingChallenge.task.exception.InputValidationException;
import com.codingChallenge.task.model.Customer;
import com.codingChallenge.task.model.User;
import com.codingChallenge.task.repository.CustomerRepository;
import com.codingChallenge.task.repository.UserRepository;

@Service
public class CustomerService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer addCustomer(Customer customer) {
		User user = customer.getUser();
		user.setRole("ROLE_CUSTOMER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		customer.setUser(user);
		return customerRepository.save(customer);
	}
	
	public Customer getById(int id) throws InputValidationException {
		Optional<Customer> option = customerRepository.findById(id);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}
}
