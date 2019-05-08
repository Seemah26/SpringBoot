package com.bl.app.user.service;

import com.bl.app.user.model.User;

public interface UserService {

	public String login(User user);

	public User userReg(User user);

}
