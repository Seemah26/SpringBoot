package com.bl.app.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bl.app.user.model.User;
import com.bl.app.user.service.EmailService;
import com.bl.app.user.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String geteUserByLogin(@RequestBody User user) {

		return userService.login(user);

	}
	

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String processForgotPasswordForm(@RequestBody User user, HttpServletRequest request) {

		emailService.forgotPassword(user.getEmail(), request);

		return "Sending mai,hello:-)";
	}
	
	@RequestMapping(value = "/resetpassword/{token}", method = RequestMethod.PUT)
	public ResponseEntity<String> resetPassword(@RequestBody User user, @PathVariable String token, HttpServletRequest request) {
		User updatedUser =emailService.resetPassword(user, token, request); 
		if (updatedUser != null) {
			return new ResponseEntity<String>("Your password has been reset", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Your password couldn't be reset", HttpStatus.CONFLICT);
	}
}


