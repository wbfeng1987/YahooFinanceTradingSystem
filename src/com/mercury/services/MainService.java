package com.mercury.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.mercury.beans.User;
import com.mercury.daos.UserDao;

@Service
public class MainService {
	@Autowired
	private UserDao ud;
	
	//@Transactional
	public void signUp(User user) {
		ud.save(user);
	}
}
