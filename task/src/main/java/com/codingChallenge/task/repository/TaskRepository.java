package com.codingChallenge.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingChallenge.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
