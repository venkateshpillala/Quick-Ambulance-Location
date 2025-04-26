package com.location.fiegn;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.location.dto.LiveLocation;


@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
public interface ILiveLocationFeignService {

	@PostMapping("/driver-live-location")
	public ResponseEntity<String> saveDriverLiveLocation(@RequestBody LiveLocation driverLiveLocation);
	
	@GetMapping("/driver-live-location")
	public ResponseEntity<List<LiveLocation>> getAllDriverLiveLocations();
	
	
}
