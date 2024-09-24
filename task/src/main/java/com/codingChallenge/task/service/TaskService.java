package com.codingChallenge.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingChallenge.task.exception.InputValidationException;
import com.codingChallenge.task.model.Customer;
import com.codingChallenge.task.model.Task;
import com.codingChallenge.task.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TaskRepository taskRepository;

	public Task addTask(int customerId, Task task) throws InputValidationException {
		Customer customer = customerService.getById(customerId);
		task.setCustomer(customer);
		return taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

}
