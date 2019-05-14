package com.bl.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bl.app.model.UserInfo;
import com.bl.app.service.UserService;

@RestController
public class UserRegistraion {

	
	@Autowired
	private UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public UserInfo createUser(@RequestBody UserInfo user,HttpServletRequest request) {

        return userService.userRegistration(user,request);
    }
}