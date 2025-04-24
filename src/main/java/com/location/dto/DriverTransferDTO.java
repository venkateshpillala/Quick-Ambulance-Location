package com.location.dto;

public class DriverTransferDTO {

	private String userUsername;
	private Double longitude;
	private Double latitude;
	private Long phone;
	private Double distance;
	
	public DriverTransferDTO(String userUsername, Double longitude, Double latitude, Long phone, Double distance) {
		super();
		this.userUsername = userUsername;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phone = phone;
		this.distance = distance;
	}
	
	public Double getDistance() {
		return distance;
	}
	
	public String getUserUsername() {
		return userUsername;
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
		return "DriverDTO [userUsername=" + userUsername + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", phone=" + phone + "]";
	}
	
	
}
