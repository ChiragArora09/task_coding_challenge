package com.codingChallenge.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codingChallenge.task.model.Task;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE FROM Task t WHERE id=?1 AND t.customer.id=?2")
	Object deleteTask(int taskId, int customerId);

}
