package com.bl.app.service;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bl.app.model.UserInfo;

public interface UserService {
	public String login(UserInfo user);

	public UserInfo update(String token, UserInfo user);

	public UserInfo userRegistration(UserInfo user);

	public String encryptedPassword(UserInfo user);

	public int tokenVerification(String token);

	String jwtToken(String subject, int id);

	public boolean delete(String token);
	
	
	public UserInfo getUserInfoByEmail(String email);
	
	public  String sendMail(UserInfo user,HttpServletRequest request,String token);

	public Optional<UserInfo> findById(int id);

	

}
