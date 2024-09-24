package com.codingChallenge.task.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingChallenge.task.dto.MessageDto;
import com.codingChallenge.task.exception.InputValidationException;
import com.codingChallenge.task.model.Task;
import com.codingChallenge.task.service.TaskService;
import com.codingChallenge.task.utility.GetIdByUsername;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private GetIdByUsername getIdByUsername;
	
	
	
	@PostMapping("/add-task")
	public ResponseEntity<?> addTask(Principal principal, @RequestBody Task task, MessageDto dto){
		try {
			String customerUserName = principal.getName();
			int customerId = getIdByUsername.getIdByUsername(customerUserName);
			System.out.println(customerId);
			task = taskService.addTask(customerId, task);
			return ResponseEntity.ok(task);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all-tasks")
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<?> getTaskById(@PathVariable int taskId, MessageDto dto){
		try {
			Task task = taskService.findById(taskId);
			return ResponseEntity.ok(task);
		}catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/{taskId}/update")
	public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody Task task, MessageDto dto){
		try {
			Task updatedTask = taskService.updateTask(taskId, task);
			return ResponseEntity.ok(updatedTask);
		} catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

}
