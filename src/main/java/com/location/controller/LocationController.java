package com.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.location.comp.BookStore;
import com.location.dto.BookingInfo;
import com.location.dto.DriverTransferDTO;
import com.location.dto.LiveLocation;
import com.location.dto.TomTomResponse;
import com.location.dto.TrackDetailsDTO;
import com.location.dto.UserTransferDTO;
import com.location.service.IDriverLogsService;
import com.location.service.IDriverService;
import com.location.service.ILiveLocationService;
import com.location.service.ILocationService;
import com.location.service.IRolesService;
import com.location.service.ITrackDetailsService;
import com.location.service.IUserService;

@Controller
public class LocationController {

	@Autowired
	private ILocationService locationService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private IRolesService roleService;

	@Autowired
	private ILiveLocationService liveLocationService;

	@Autowired
	private BookStore bookStore;

	@Autowired
	private IDriverLogsService driverLogsService;

	@Autowired
	private ITrackDetailsService trackService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDriverService driverService;
	
	/*
	 * @MessageMapping("/live-location")
	 * 
	 * @SendTo("/topic/location-name") public TomTomResponse
	 * sendLocationName(LiveLocation location) { System.out.println(location);
	 * return locationService.getFullAddress(location);
	 * 
	 * }
	 */

	// Send all the clients live-location for every 3 seconds
	@MessageMapping("/live-location")
	public void sendLocationName(LiveLocation location) {
		System.out.println(location);
		/*
		 * String role =
		 * roleService.getRoleByUsername(location.getUsername()).getRole(); if
		 * (role.equalsIgnoreCase("DRIVER")) { String username =
		 * liveLocationService.saveDriverLiveLocation(location); }
		 */
		messagingTemplate.convertAndSend("/topic/location-name", locationService.getFullAddress(location));
	}

	// To show on map after login
	// Send only user live-location for every 3 seconds
	@MessageMapping("/send-user-location")
	public void nearByAmbulance(LiveLocation userLiveLocation) {
		List<LiveLocation> nearByDrivers = liveLocationService.getNearByDrivers(userLiveLocation);
		messagingTemplate.convertAndSend("/topic/near-by-ambulance", nearByDrivers);
	}

	// Send user live location when he clicks on book button
	@MessageMapping("/book")
	public void bookAmbulance(LiveLocation userLiveLocation) {
		bookStore.save("userLiveLocation", userLiveLocation);
		List<LiveLocation> nearByDrivers = liveLocationService.getNearByDrivers(userLiveLocation);
		for (LiveLocation driverLiveLocation : nearByDrivers) {
			String message = userLiveLocation.getUsername();
			String topic = "/topic/alert";
			messagingTemplate.convertAndSend(topic, message);
		}
	}

	@MessageMapping("/accept")
	public void acceptRequest(BookingInfo driverLiveLocation) {
		LiveLocation userLiveLocation = (LiveLocation) bookStore.get(driverLiveLocation.getUserUsername());
		String vehicleNumber = driverLogsService.getDriverLogsByUsernameAndDate(driverLiveLocation.getDriverUsername())
				.getAmbulance().getVehicleNumber();
		Long driverPhone = driverService.getDriverByUsername(driverLiveLocation.getDriverUsername()).getPhone();
		Long userPhone = userService.findUserByUsername(userLiveLocation.getUsername()).getPhone();
		Double distance = liveLocationService.calculateDistance(userLiveLocation, this.convert(driverLiveLocation));
		Long status = 0L;
		
		if (userLiveLocation.getUsername().equals(driverLiveLocation.getUserUsername())) {
			TrackDetailsDTO trackDetailsDto = new TrackDetailsDTO();
			trackDetailsDto.setDriverName(driverLiveLocation.getDriverUsername());
			trackDetailsDto.setUsername(userLiveLocation.getUsername());
			trackDetailsDto.setVehicleNumber(vehicleNumber);
			trackDetailsDto.setPickup(this.convertLocationToString(userLiveLocation));
			
			status = trackService.saveBookingDetails(trackDetailsDto);
		}
		
		UserTransferDTO userDTO = null;
		DriverTransferDTO driverDTO = null;
			
		if(status != 0) {
			userDTO = new UserTransferDTO(driverLiveLocation.getDriverUsername(), vehicleNumber, driverLiveLocation.getLongitude(), driverLiveLocation.getLatitude(), driverPhone, distance);
			driverDTO = new DriverTransferDTO(userLiveLocation.getUsername(), userLiveLocation.getLongitude(), userLiveLocation.getLatitude(), userPhone, distance);
		}
		
		if(userDTO != null && driverDTO != null) {
			String userTopic = "/topic/user/"+userLiveLocation.getUsername();
			String driverTopic = "/topic/driver/"+driverLiveLocation.getDriverUsername();
			messagingTemplate.convertAndSend(userTopic, userDTO);
			messagingTemplate.convertAndSend(driverTopic, driverDTO);
		}
		
		if(distance < 0.001)
			bookStore.remove(userLiveLocation.getUsername());
	}
	
	
	private LiveLocation convert(BookingInfo info) {
		LiveLocation live = new LiveLocation();
		live.setLatitude(info.getLatitude());
		live.setLongitude(info.getLongitude());
		live.setUsername(info.getDriverUsername());
		return live;
	}
	
	private String convertLocationToString(LiveLocation live) {
		String pickup = locationService.getFullAddress(live).getAddresses().get(0).getAddress().getStreetNumber()+" "+locationService.getFullAddress(live).getAddresses().get(0).getAddress().getStreetName()+
				" "+locationService.getFullAddress(live).getAddresses().get(0).getAddress().getMunicipality()+" "+
				locationService.getFullAddress(live).getAddresses().get(0).getAddress().getMunicipality();
		return pickup;
	}

}
