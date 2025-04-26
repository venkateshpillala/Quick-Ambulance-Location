package com.location.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.location.dto.Driver;


@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
public interface IDriverFeignService {
	@GetMapping("/driver")
	public ResponseEntity<Driver> findByUsername(@RequestParam String username);
	
}
