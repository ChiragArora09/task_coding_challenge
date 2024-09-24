package com.codingChallenge.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingChallenge.task.exception.InputValidationException;
import com.codingChallenge.task.model.Customer;
import com.codingChallenge.task.model.Task;
import com.codingChallenge.task.repository.CustomerRepository;
import com.codingChallenge.task.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public Task addTask(int customerId, Task task) throws InputValidationException {
		Customer customer = customerService.getById(customerId);
		task.setCustomer(customer);
		return taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task findById(int taskId) throws InputValidationException {
		Optional<Task> option = taskRepository.findById(taskId);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		return option.get();
	}

	public Task updateTask(int taskId, Task task) throws InputValidationException {
		Optional<Task> option = taskRepository.findById(taskId);
		if(option.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		Task previousTask = option.get();
		
		previousTask.setTitle(task.getTitle());
		previousTask.setDescription(task.getDescription());
		previousTask.setStatus(task.getStatus());
		previousTask.setPriority(task.getPriority());
		previousTask.setDueDate(task.getDueDate());
		
		return taskRepository.save(previousTask);
	}

	public Object deleteTask(int customerId, int taskId) throws InputValidationException {
		Optional<Task> tOption = taskRepository.findById(taskId);
		if(tOption.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		Optional<Customer> cOption = customerRepository.findById(customerId);
		if(cOption.isEmpty()) {
			throw new InputValidationException("Invalid ID");
		}
		
		return taskRepository.deleteTask(taskId, customerId);
	}
}
