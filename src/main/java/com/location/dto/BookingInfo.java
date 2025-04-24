package com.location.dto;

public class BookingInfo {

	private String userUsername;
	private String driverUsername;
	private Double longitude;
	private Double latitude;
	
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getDriverUsername() {
		return driverUsername;
	}
	public void setDriverUsername(String driverUsername) {
		this.driverUsername = driverUsername;
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
		return "DriverAlerts [userUsername=" + userUsername + ", driverUsername=" + driverUsername + ", longitude="
				+ longitude + ", latitude=" + latitude + "]";
	}
	
}
