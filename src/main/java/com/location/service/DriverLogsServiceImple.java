package com.location.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.DriverLogs;
import com.location.fiegn.IDriverLogsFeignService;

@Service
public class DriverLogsServiceImple implements IDriverLogsService {
	
	@Autowired
	private IDriverLogsFeignService driverFeign;

	@Override
	public DriverLogs getDriverLogsByUsernameAndDate(String username) {
		return driverFeign.getDriverLogsByUsernameAndDate(username, LocalDate.now()).getBody();
	}

}
