package com.location.fiegn;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.location.dto.DriverLogs;


@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
public interface IDriverLogsFeignService {

	@GetMapping("/driver-logs/username-date")
	public ResponseEntity<DriverLogs> getDriverLogsByUsernameAndDate(@RequestParam String username, @RequestParam LocalDate date);
		
}
