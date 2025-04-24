package com.location.dto;


public class LiveLocation {

	private String username;
	private Double longitude;
	private Double latitude;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "DriverLiveLocation [username=" + username + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
}
