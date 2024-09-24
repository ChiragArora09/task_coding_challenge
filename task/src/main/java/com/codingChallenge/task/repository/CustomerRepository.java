package com.codingChallenge.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingChallenge.task.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
