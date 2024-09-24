package com.codingChallenge.task.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingChallenge.task.repository.UserRepository;


@Service
public class GetIdByUsername {
	
	@Autowired
	private UserRepository userRepository;
	
	public int getIdByUsername(String username) {
		int id=-1;
		List<Object[]> clist = userRepository.getCustomerId(username);
		Object[] cid = clist.get(0);
		id = (int) cid[0];
		return id;
	}
	
}
