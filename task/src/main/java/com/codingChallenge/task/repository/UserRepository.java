package com.codingChallenge.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codingChallenge.task.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

// select c.id from user u JOIN customer c ON c.user_id=u.id WHERE u.username="chirag";
	@Query("SELECT c.id FROM Customer c JOIN c.user u WHERE u.username=?1")
	List<Object[]> getCustomerId(String username);

}
