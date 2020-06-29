package com.anju.eventmanagement.service;

import com.anju.eventmanagement.model.User;

public interface UserService extends CrudService<User, Long> {
	public User getUserByUsernameAndPassword(String username, String password);
	public User getUserByUsername(String username);
}
