package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.Driver;
import com.location.fiegn.IDriverFeignService;
@Service
public class DriverServiceImple implements IDriverService {
	
	@Autowired
	private IDriverFeignService driverFeign;

	@Override
	public Driver getDriverByUsername(String username) {
		return driverFeign.findByUsername(username).getBody();
	}

}
