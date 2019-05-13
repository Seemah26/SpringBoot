package com.bl.app.user.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bl.app.user.model.User;
import com.bl.app.user.service.EmailService;
import com.bl.app.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserService userService;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	@Override
	public boolean forgotPassword(String emailId, HttpServletRequest request) {

		User user = userService.findUserByEmail(emailId);

		System.out.println("---->" + user.getEmail());
		String token = userService.jwtToken("secretKey", user.getEmail());

		StringBuffer requestUrl = request.getRequestURL();
		System.out.println(requestUrl);
		String forgotPasswordUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/"));
		forgotPasswordUrl = forgotPasswordUrl + "/resetpassword/" + "token=" + token;
		System.out.println(forgotPasswordUrl);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("seemaswamy026@gmail.com");
		msg.setTo(emailId);
		msg.setSubject("Verification Email");
		String message = "Please click on the link below to verify email Id /n/n " + forgotPasswordUrl;
		msg.setText(message);
		msg.setSentDate(new Date());
		mailSender.send(msg);

		return true;

	}

	@Override
	public User resetPassword(User user, String token, HttpServletRequest request) {
		int id = verifyToken(token);
		//User existingUser = userService.findUserByEmail(user.getEmail());
		User existingUser = userService.findById(id);
		if (existingUser != null) {
			existingUser.setPassword(userService.securePassword(user));// bcryptEncoder.encode(user.getPassword()));
			userService.saveUser(existingUser);
			return existingUser;
		}
		return null;
	}

	public String generateToken(String id) {
		return Jwts.builder().setId(id).claim("roles", "existingUser").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
	}

	public int verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secretKey"))
				.parseClaimsJws(token).getBody();
		System.out.println("ID: " + claims.getId());
		return Integer.parseInt(claims.getId());

	}

}
