package com.location.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class DriverDTO {

	private String username;
	private String password;
	private String driverName;
	private LocalDate dob;
	private Long phone;
	private String email;
	private MultipartFile license;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public MultipartFile getLicense() {
		return license;
	}
	public void setLicense(MultipartFile license) {
		this.license = license;
	}
	@Override
	public String toString() {
		return "DriverDTO [username=" + username + ", driverName=" + driverName + ", dob=" + dob + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
	
}
