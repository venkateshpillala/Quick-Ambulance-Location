package com.location.dto;

public class Ambulance {

	private String vehicleNumber;
	
	private String color;
	private Long yearOfModel;
	private String company;
	
	private byte[] cbook;
	
	private Boolean status;

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getYearOfModel() {
		return yearOfModel;
	}

	public void setYearOfModel(Long yearOfModel) {
		this.yearOfModel = yearOfModel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public byte[] getCbook() {
		return cbook;
	}

	public void setCbook(byte[] cbook) {
		this.cbook = cbook;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Ambulance [vehicleNumber=" + vehicleNumber + ", color=" + color + ", yearOfModel=" + yearOfModel
				+ ", company=" + company + "]";
	}
	
}
