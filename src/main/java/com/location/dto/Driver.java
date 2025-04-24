package com.location.dto;

import java.time.LocalDate;

public class Driver {

	private String username;
	
	private String driverName;
	
	private LocalDate dob;
	
	private Long phone;
	
	private String email;
	
	private byte[] license;
	
	private Boolean status;
	
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Driver [username=" + username + ", driverName=" + driverName + ", dob=" + dob + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
	
	
}
