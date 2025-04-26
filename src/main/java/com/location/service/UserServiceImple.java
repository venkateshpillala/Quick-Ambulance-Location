package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.User;
import com.location.exception.ResourceNotFoundException;
import com.location.fiegn.IUserFeignService;
@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserFeignService userFeign;
	
	@Override
	public User findUserByUsername(String username) {
		User user = userFeign.findByUsername(username).getBody();
		if(user == null)
			throw new ResourceNotFoundException("RESOURCE_NOT_FOUND "+username);
		return user;
	}

}
