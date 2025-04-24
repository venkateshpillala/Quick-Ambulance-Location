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
		return feignService.saveTrackDetails(trackDeatails).getBody();
	}

}
