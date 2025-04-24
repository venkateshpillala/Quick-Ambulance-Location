package com.location.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class DriverLogs {

    private Long logid; 
    
    private LocalDate loginDate; 
    private LocalTime login; 
    private LocalTime logout; 
    
    private Driver driver; 

    private Ambulance ambulance;

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long loginid) {
		this.logid = loginid;
	}

	public LocalDate getLogDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}

	public LocalTime getLogin() {
		return login;
	}

	public void setLogin(LocalTime login) {
		this.login = login;
	}

	public LocalTime getLogout() {
		return logout;
	}

	public void setLogout(LocalTime logout) {
		this.logout = logout;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Ambulance getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	@Override
	public String toString() {
		return "DriverLogs [loginid=" + logid + ", date=" + loginDate + ", login=" + login + ", logout=" + logout
				+ ", driver=" + driver + ", ambulance=" + ambulance + "]";
	} 

    
    
}
