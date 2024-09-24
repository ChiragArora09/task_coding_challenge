package com.codingChallenge.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingChallenge.task.enums.PriorityType;
import com.codingChallenge.task.enums.StatusType;
import com.codingChallenge.task.exception.InputValidationException;
import com.codingChallenge.task.model.Customer;
import com.codingChallenge.task.model.Task;
import com.codingChallenge.task.repository.TaskRepository;
import com.codingChallenge.task.service.CustomerService;
import com.codingChallenge.task.service.TaskService;

@SpringBootTest
public class TaskServiceTest {
	
	@Mock
	private CustomerService customerService;
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskService taskService;
	
	@Test
	public void addTaskTest() throws InputValidationException {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFullname("Chirag Arora");
		customer.setEmail("chirag@gmail.com");
		customer.setContactNumber("9283493499");
		
		Task task = new Task();
		task.setId(1);
		task.setTitle("Task 1");
		task.setDescription("Description of task 1");
		task.setStatus(StatusType.Pending);
		task.setPriority(PriorityType.High);
		task.setDueDate(LocalDate.now());
		
		when(customerService.getById(1)).thenReturn(customer);
		when(taskRepository.save(task)).thenReturn(task);
		
		Task savedTask = taskService.addTask(1, task);
		
		assertEquals(1, savedTask.getId());
		assertEquals("Task 1", savedTask.getTitle());
		assertEquals("Description of task 1", savedTask.getDescription());
		assertEquals("Chirag Arora", savedTask.getCustomer().getFullname());
	}

}
