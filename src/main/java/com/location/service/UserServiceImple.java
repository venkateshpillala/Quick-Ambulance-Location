package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.User;
import com.location.fiegn.IUserFeignService;
@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserFeignService userFeign;
	
	@Override
	public User findUserByUsername(String username) {
		return userFeign.findByUsername(username).getBody();
	}

}
