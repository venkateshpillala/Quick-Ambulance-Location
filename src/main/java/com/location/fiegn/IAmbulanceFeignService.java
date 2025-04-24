package com.location.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.location.dto.Ambulance;


@FeignClient("QUICK-AMBULANCE-DATABASE")
public interface IAmbulanceFeignService {
	@GetMapping("/ambulance")
	public ResponseEntity<Ambulance> findVehicleByVehicleNumber(@RequestParam String vehicleNumber);
	
}
