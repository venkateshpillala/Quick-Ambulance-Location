package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.Roles;
import com.location.fiegn.IRolesFiegnService;

@Service
public class RolesServiceImple implements IRolesService {
	
	@Autowired
	private IRolesFiegnService roleFeign;

	@Override
	public Roles getRoleByUsername(String username) {
		Roles role = null;
		try {
			role = roleFeign.getRolesByUsername(username).getBody();
			role.setPassword(null);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return role;
	}

}
