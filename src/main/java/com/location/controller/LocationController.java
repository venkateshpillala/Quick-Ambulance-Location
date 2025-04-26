package com.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.location.comp.BookStore;
import com.location.dto.BookingInfo;
import com.location.dto.DriverTransferDTO;
import com.location.dto.LiveLocation;
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
	 * Front-end sends users(driver, user) live location(longitude and latitude) for
	 * every 3 seconds If user -> directly sends location name If driver -> Maintain
	 * location in DB and sends location name
	 */

	@MessageMapping("/live-location")
	public void sendLocationName(LiveLocation location) {
		System.out.println(location);
		try {
			String role = roleService.getRoleByUsername(location.getUsername()).getRole();
			if (role.equalsIgnoreCase("DRIVER")) {
				String username = liveLocationService.saveDriverLiveLocation(location);
			}

			messagingTemplate.convertAndSend("/topic/location-name/" + location.getUsername(),
					locationService.getFullAddress(location));
		} catch (Exception e) {
			messagingTemplate.convertAndSend("/topic/error/" + location.getUsername(), e.getMessage());
		}
	}

	/*
	 * This methods sends all near by driver locations to show on user's Map
	 */

	@MessageMapping("/driver-locations")
	public void nearByAmbulance(LiveLocation userLiveLocation) {
		try {
			List<LiveLocation> nearByDrivers = liveLocationService.getNearByDrivers(userLiveLocation);
			messagingTemplate.convertAndSend("/topic/near-by-ambulance/" + userLiveLocation.getUsername(),
					nearByDrivers);
		} catch (Exception e) {
			messagingTemplate.convertAndSend("/topic/error/" + userLiveLocation.getUsername(), e.getMessage());
		}
	}

	/*
	 * When user clicks on Book button, this method sends an alert messages to all
	 * the drivers near by 20KM radius
	 */

	@MessageMapping("/book")
	public void bookAmbulance(LiveLocation userLiveLocation) {
		try {
			bookStore.save(userLiveLocation.getUsername(), userLiveLocation);
			List<LiveLocation> nearByDrivers = liveLocationService.getNearByDrivers(userLiveLocation);
			for (LiveLocation driverLiveLocation : nearByDrivers) {
				String message = userLiveLocation.getUsername();
				String topic = "/topic/alert/" + driverLiveLocation.getUsername();
				messagingTemplate.convertAndSend(topic, message);
			}
		} catch (Exception e) {
			messagingTemplate.convertAndSend("/topic/error/" + userLiveLocation.getUsername(), e.getMessage());
		}
	}

	/*
	 * If any one driver accepts the booking, this method save the booking details
	 * in DB and send user details to driver and driver details to user
	 */

	@MessageMapping("/accept")
	public void acceptBooking(BookingInfo driverLiveLocation) {
		try {
			LiveLocation userLiveLocation = (LiveLocation) bookStore.get(driverLiveLocation.getUserUsername());
			String vehicleNumber = driverLogsService
					.getDriverLogsByUsernameAndDate(driverLiveLocation.getDriverUsername()).getAmbulance()
					.getVehicleNumber();
			Long driverPhone = driverService.getDriverByUsername(driverLiveLocation.getDriverUsername()).getPhone();
			Long userPhone = userService.findUserByUsername(userLiveLocation.getUsername()).getPhone();
			Double distance = liveLocationService.calculateDistance(userLiveLocation, this.convert(driverLiveLocation))
					/ 1000.0;
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

			if (status != 0) {
				userDTO = new UserTransferDTO(driverLiveLocation.getDriverUsername(), vehicleNumber,
						driverLiveLocation.getLongitude(), driverLiveLocation.getLatitude(), driverPhone, distance);
				driverDTO = new DriverTransferDTO(userLiveLocation.getUsername(), userLiveLocation.getLongitude(),
						userLiveLocation.getLatitude(), userPhone, distance);
			}

			if (userDTO != null && driverDTO != null) {
				String userTopic = "/topic/user/" + userLiveLocation.getUsername();
				String driverTopic = "/topic/driver/" + driverLiveLocation.getDriverUsername();
				messagingTemplate.convertAndSend(userTopic, userDTO);
				messagingTemplate.convertAndSend(driverTopic, driverDTO);
			}
		} catch (Exception e) {
			messagingTemplate.convertAndSend("/topic/error/" + driverLiveLocation.getUserUsername(),
					e.getMessage());
			messagingTemplate.convertAndSend("/topic/error/" + driverLiveLocation.getDriverUsername(),
					e.getMessage());

		}
	}

	@MessageMapping("/driver-live-updates")
	public void sendDriverLive(BookingInfo driverLiveLocation) {
		try {
			LiveLocation userLiveLocation = (LiveLocation) bookStore.get(driverLiveLocation.getUserUsername());
			Double distance = liveLocationService.calculateDistance(userLiveLocation, this.convert(driverLiveLocation));

			UserTransferDTO userDTO = null;
			DriverTransferDTO driverDTO = null;

			userDTO = new UserTransferDTO(driverLiveLocation.getDriverUsername(), null,
					driverLiveLocation.getLongitude(), driverLiveLocation.getLatitude(), null, distance / 1000.0);
			driverDTO = new DriverTransferDTO(userLiveLocation.getUsername(), userLiveLocation.getLongitude(),
					userLiveLocation.getLatitude(), null, distance / 1000.0);

			if (userDTO != null && driverDTO != null) {
				String userTopic = "/topic/user/" + userLiveLocation.getUsername();
				String driverTopic = "/topic/driver/" + driverLiveLocation.getDriverUsername();
				messagingTemplate.convertAndSend(userTopic, userDTO);
				messagingTemplate.convertAndSend(driverTopic, driverDTO);
			}
			if (Math.abs(distance - 1.0) < 0.1)
				bookStore.remove(userLiveLocation.getUsername());
		} catch (Exception e) {
			messagingTemplate.convertAndSend("/topic/error/" + driverLiveLocation.getUserUsername(),
					e.getMessage());
			messagingTemplate.convertAndSend("/topic/error/" + driverLiveLocation.getDriverUsername(),
					e.getMessage());

		}
	}

	private LiveLocation convert(BookingInfo info) {
		LiveLocation live = new LiveLocation();
		live.setLatitude(info.getLatitude());
		live.setLongitude(info.getLongitude());
		live.setUsername(info.getDriverUsername());
		return live;
	}

	private String convertLocationToString(LiveLocation live) {
		String pickup = locationService.getFullAddress(live).getAddresses().get(0).getAddress().getStreetNumber() + " "
				+ locationService.getFullAddress(live).getAddresses().get(0).getAddress().getStreetName() + " "
				+ locationService.getFullAddress(live).getAddresses().get(0).getAddress().getMunicipality() + " "
				+ locationService.getFullAddress(live).getAddresses().get(0).getAddress().getMunicipality();
		return pickup;
	}

}
