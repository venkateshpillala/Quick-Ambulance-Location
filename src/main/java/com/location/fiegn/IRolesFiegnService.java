package com.location.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.location.dto.Roles;


@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
//@FeignClient("QUICK-AMBULANCE-DATABASE")
public interface IRolesFiegnService {

	@GetMapping("/roles")
	public ResponseEntity<Roles> getRolesByUsername(@RequestParam String username);
	
}
