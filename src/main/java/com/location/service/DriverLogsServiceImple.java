package com.location.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.exception.ResourceNotFoundException;
import com.location.fiegn.IDriverLogsFeignService;

@Service
public class DriverLogsServiceImple implements IDriverLogsService {
	
	@Autowired
	private IDriverLogsFeignService driverFeign;

	@Override
	public Map<String, Object> getDriverVehicleNumberAndPhone(String username) {
		Map<String, Object> response = driverFeign.getDriverPhoneAndVehicleNumber(username).getBody();
		if(response == null)
			throw new ResourceNotFoundException("RESOURCE_NOT_FOUND "+username);
		return response;
	}

}
