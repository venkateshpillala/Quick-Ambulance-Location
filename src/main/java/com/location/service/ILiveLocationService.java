package com.location.service;

import java.util.List;

import com.location.dto.LiveLocation;

public interface ILiveLocationService {

	public String saveDriverLiveLocation(LiveLocation liveLocation);
	public List<LiveLocation> getNearByDrivers(LiveLocation userLiveLocation);
	public double calculateDistance(LiveLocation userLiveLocation, LiveLocation driverLiveLocation);

}
