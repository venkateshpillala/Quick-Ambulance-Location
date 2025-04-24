package com.location.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

	private Integer status;
	private String message;
	private LocalDateTime timeStamp;
	
	public ErrorResponseDTO(Integer status, String message, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	public Integer getCode() {
		return status;
	}
	public void setCode(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "ErrorResponseDTO [code=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
