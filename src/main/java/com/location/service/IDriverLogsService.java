package com.location.service;

import com.location.dto.DriverLogs;

public interface IDriverLogsService {

	public DriverLogs getDriverLogsByUsernameAndDate(String username);
}
