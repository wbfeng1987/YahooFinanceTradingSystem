package com.mercury.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.beans.User;
import com.mercury.daos.UserDao;

@Service
public class LoginService {
	
	@Autowired
	private UserDao ud;
	
	//@Transactional
	public void signUp(User user) {
		user.setAuthority("ROLE_USER");
		user.setBalance(0);
		user.setCash(0);
		user.setEnable(0);
		ud.save(user);
	}

}
