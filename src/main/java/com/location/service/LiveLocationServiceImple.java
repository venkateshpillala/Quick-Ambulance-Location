package com.location.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.dto.LiveLocation;
import com.location.exception.ResourceNotFoundException;
import com.location.fiegn.ILiveLocationFeignService;

@Service
public class LiveLocationServiceImple implements ILiveLocationService {
	
	@Autowired
	private ILiveLocationFeignService feignService;

	@Override
	public String saveDriverLiveLocation(LiveLocation liveLocation) {
		String username = feignService.saveDriverLiveLocation(liveLocation).getBody();
		if(username == null)
			throw new RuntimeException("INTERNAL_SERVER_ERROR_not_Saved");
		return username;
	}

	@Override
	public List<LiveLocation> getNearByDrivers(LiveLocation userLiveLocation) {
		List<LiveLocation> list = feignService.getAllDriverLiveLocations().getBody();
		if(list == null) {
			throw new ResourceNotFoundException("NO_DRIVERS_AVAILABLE");
		}
		List<LiveLocation> nearByDrivers = new ArrayList<LiveLocation>();
		for(LiveLocation driverLiveLocation:list) {
			Double distance = this.calculateDistance(userLiveLocation, driverLiveLocation);
			System.out.println(distance);
			if(distance<=2000.0) 
				nearByDrivers.add(driverLiveLocation);
		}
		return nearByDrivers;
	}

	@Override
	public double calculateDistance(LiveLocation userLiveLocation, LiveLocation driverLiveLocation) {
	    final double EARTH_RADIUS_KM = 6371000;

	    double lat1 = userLiveLocation.getLatitude();
	    double lon1 = userLiveLocation.getLongitude();
	    double lat2 = driverLiveLocation.getLatitude();
	    double lon2 = driverLiveLocation.getLongitude();

	    // Convert degrees to radians
	    double lat1Rad = Math.toRadians(lat1);
	    double lon1Rad = Math.toRadians(lon1);
	    double lat2Rad = Math.toRadians(lat2);
	    double lon2Rad = Math.toRadians(lon2);

	    // Haversine formula
	    double dlat = lat2Rad - lat1Rad;
	    double dlon = lon2Rad - lon1Rad;

	    double a = Math.pow(Math.sin(dlat / 2), 2)
	             + Math.cos(lat1Rad) * Math.cos(lat2Rad)
	             * Math.pow(Math.sin(dlon / 2), 2);

	    double c = 2 * Math.asin(Math.sqrt(a));

	    return EARTH_RADIUS_KM * c;
	}

	
}
