package com.codingChallenge.task.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codingChallenge.task.model.User;
import com.codingChallenge.task.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Optional<User> userOptional = userRepository.findByUsername(username);
	        
	        // Check if user exists
	        if (!userOptional.isPresent()) {
	            throw new UsernameNotFoundException("User not found: " + username);
	        }

	        User user = userOptional.get();
		
 
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

}
