package com.cetpa.services_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entity.Account;
import com.cetpa.entity.User;
import com.cetpa.repository.AccountRepository;
import com.cetpa.repository.UserRepository;
import com.cetpa.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	public int RegisterUser(User user) {

    userRepository.save( user);
    Account account = new Account();
    account.setUserId(user.getUserid());
    accountRepository.save(account);
	return account.getAccountNo();
	}

	@Override
	public User getUser(String userid) {
		// TODO Auto-generated method stub
		User user= userRepository.findById(userid).orElse(null);
		return user ;
	}
}
