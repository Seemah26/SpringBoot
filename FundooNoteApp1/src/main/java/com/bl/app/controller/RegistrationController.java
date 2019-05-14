package com.bl.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bl.app.model.UserInfo;
import com.bl.app.service.UserService;


@RestController

public class RegistrationController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public UserInfo createUser(@RequestBody UserInfo user) {

		return userService.userRegistration(user);
	}
	


}
