package com.location.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TomTomResponse {
	private List<AddressResponse> addresses;

	public List<AddressResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressResponse> addresses) {
		this.addresses = addresses;
	}
	
}
