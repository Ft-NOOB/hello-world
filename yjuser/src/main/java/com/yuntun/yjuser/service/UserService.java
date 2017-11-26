package com.yuntun.yjuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuntun.yjuser.dao.UserDao;
import com.yuntun.yjuser.domain.User;
import com.yuntun.yjuser.forms.UserForm;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void login(UserForm userForm) {
		
	}
	
	public void register(UserForm userForm) {
		
	}
	
	public User save(User user) {
		userDao.save(user);
		return user;
	}
	
	public String delete(String id) {
		userDao.delete(id);
		return id;
	}
	
	public User findById(String id) {
		return userDao.findById(id);
	}

}
