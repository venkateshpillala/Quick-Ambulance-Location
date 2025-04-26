package com.location.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.TrackDetailsDTO;
import com.location.fiegn.ITrackDetailsFeignService;

@Service
public class TrackDetailsServiceImple implements ITrackDetailsService {

	@Autowired
	private ITrackDetailsFeignService feignService;
	
	@Override
	public Long saveBookingDetails(TrackDetailsDTO trackDeatails) {
		Long status = feignService.saveTrackDetails(trackDeatails).getBody();
		if(status == null)
			throw new RuntimeException("INTERNAL_SERVER_ERROR_not_saved");
		return status;
	}

}
