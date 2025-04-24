package com.location.service;

import com.location.dto.User;

public interface IUserService {

	public User findUserByUsername(String username);
}
