package com.bl.app.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.SimpleMailMessage;

import com.bl.app.user.model.User;


public interface EmailService {
	
	public void sendEmail(SimpleMailMessage email);
	 
	public boolean forgotPassword(String emailId, HttpServletRequest request) ;
	public User resetPassword(User user, String token, HttpServletRequest request);
	
}