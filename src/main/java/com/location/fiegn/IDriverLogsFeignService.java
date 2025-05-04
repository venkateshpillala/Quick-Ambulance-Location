package com.location.fiegn;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient("QUICK-AMBULANCE-DATABASE")
@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
public interface IDriverLogsFeignService {

	@GetMapping("/driver-logs/vehicle-phone")
	public ResponseEntity<Map<String, Object>> getDriverPhoneAndVehicleNumber(@RequestParam String username);
		
}
