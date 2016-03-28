package com.mercury.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.User;
import com.mercury.services.LoginService;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @version update 03/24/2016
 * @author Weibo
 * 
 * Test Login controller
*/

@Controller
@SessionAttributes
public class LoginController {
	
	@Autowired
	private LoginService ls;
	
	//for login interface
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login() {
		System.out.println("in login");
		return "testSignInAndUp";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() {
		System.out.println("at home");
		return "home";
	}
	
	// for sign up 
	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute("user") User user, BindingResult result) {
		System.out.println(user);
		ls.signUp(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}


}
