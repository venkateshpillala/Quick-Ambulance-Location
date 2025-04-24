package com.location.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.location.dto.LiveLocation;
import com.location.dto.TomTomResponse;

@Service
public class LocationServiceImple implements ILocationService{
	
	private static final String API_KEY="WBpfLHoF5JNyMmdjxNJG9J6sEwADwAEf";

	@Override
	public TomTomResponse getFullAddress(LiveLocation location) {
		
		String url = String.format(
	            "https://api.tomtom.com/search/2/reverseGeocode/%f,%f.json?key=%s",
	            location.getLatitude(), location.getLongitude(), API_KEY
	        );

		 RestTemplate restTemplate = new RestTemplate();
	        return restTemplate.getForObject(url, TomTomResponse.class);	
	 }


}
