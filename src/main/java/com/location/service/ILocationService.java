package com.location.service;

import com.location.dto.LiveLocation;
import com.location.dto.TomTomResponse;

public interface ILocationService {

	public TomTomResponse getFullAddress(LiveLocation location);
}
