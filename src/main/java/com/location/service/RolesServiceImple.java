package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.Roles;
import com.location.exception.ResourceNotFoundException;
import com.location.fiegn.IRolesFiegnService;

@Service
public class RolesServiceImple implements IRolesService {

	@Autowired
	private IRolesFiegnService roleFeign;

	@Override
	public Roles getRoleByUsername(String username) {
		Roles role = roleFeign.getRolesByUsername(username).getBody();
		if(role == null)
			throw new ResourceNotFoundException("RESOURCE_NOT_FOUND_"+username);
		role.setPassword(null);

		return role;
	}

}
