package com.cetpa.services;

import com.cetpa.entity.User;

public interface UserService {

	

	int RegisterUser(User user);

	User getUser(String userid);

	
}
