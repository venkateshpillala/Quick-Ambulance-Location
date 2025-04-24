package com.location.dto;

public class TrackDetailsDTO {

	private String pickup;
	private String vehicleNumber;
	private String username;
	private String driverName;
	
	public String getPickup() {
		return pickup;
	}
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	@Override
	public String toString() {
		return "TrackDetailsDTO [pickup=" + pickup + ", vehicleNumber=" + vehicleNumber + ", username=" + username
				+ ", driverName=" + driverName + "]";
	}
	
	
}
