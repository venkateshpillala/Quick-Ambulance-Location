package com.location.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.location.dto.TrackDetailsDTO;
//@FeignClient("QUICK-AMBULANCE-DATABASE")
@FeignClient(name = "QUICK-AMBULANCE-DATABASE", url="https://quick-ambulance-database.onrender.com")
public interface ITrackDetailsFeignService {
	@PostMapping("/track-details")
	public ResponseEntity<Long> saveTrackDetails(@RequestBody TrackDetailsDTO dto);
	
}
