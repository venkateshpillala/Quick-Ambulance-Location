package com.location.dto;

public class UserTransferDTO {

	private String driverUsername;
	private String vehicleNumber;
	private Double longitude;
	private Double latitude;
	private Long phone;
	private Double distance;
	
	public UserTransferDTO(String driverUsername, String vehicleNumber, Double longitude, Double latitude, Long phone, Double distance) {
		super();
		this.driverUsername = driverUsername;
		this.vehicleNumber = vehicleNumber;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phone = phone;
		this.distance = distance;
	}
	
	public Double getDistance() {
		return distance;
	}
	
	public String getDriverUsername() {
		return driverUsername;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Long getPhone() {
		return phone;
	}
	@Override
	public String toString() {
		return "UserDTO [driverUsername=" + driverUsername + ", vehicleNumber=" + vehicleNumber + ", longitude="
				+ longitude + ", latitude=" + latitude + ", phone=" + phone + "]";
	}
	
	
}
