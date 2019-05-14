package com.bl.app.user.service;

import com.bl.app.user.model.User;
import com.google.common.base.Optional;

public interface UserService {

	public String login(User user);

	public User userReg(User user);
	
	public String securePassword(User user);
	
	public User saveUser(User user);
	
    public Optional<User> findUserByResetToken(String resetToken);

    public User findUserByEmail(String email);
    
    
    
    public String jwtToken(String secretKey, String subject);

	public User findById(int id);

}
