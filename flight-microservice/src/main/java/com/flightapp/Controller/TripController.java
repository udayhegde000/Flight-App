/**
 * 
 */
package com.flightapp.Controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightapp.Service.TripService;
import com.flightapp.model.Flight;
import com.flightapp.model.Trip;

/**
 * @author udayh
 *
 */
@RestController
@RequestMapping("trips")
public class TripController {

	@Autowired
	TripService tripService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getAll")
	public List<Trip> findAll() {
		return tripService.findAll();
	}

	@GetMapping("/search")
	public List<Trip> search(@RequestParam(value = "journeyDate", required = false) Date journeyDate,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> entity = new HttpEntity<Flight>(headers);
		ResponseEntity<Flight[]> responseEntity = restTemplate.exchange("http:localhost:8091:/admin/api/flights/all",
				HttpMethod.PUT, entity, Flight[].class);
		List<Trip> tripList = tripService.search(journeyDate, from, to, responseEntity.getBody());
		return tripList;
	}

	@PostMapping("/save")
	public Trip save(@RequestBody Trip trip) {
		return tripService.save(trip);
	}

	@PutMapping("/save/{tripId}")
	public Trip save(@PathVariable Long tripId, @RequestBody Trip trip) {
		return tripService.put(tripId, trip);
	}

	@DeleteMapping("/delete")
	public void Delete(@RequestBody Trip trip) {
		tripService.delete(trip);
	}

	@PutMapping("/startTrips")
	public List<Trip> startTrips(@RequestBody Flight flight) throws Exception {
		try {
			return tripService.startTrips(flight);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to start the trips");
		}
	}

	@PutMapping("/stopTrips")
	public void endTrips(@RequestBody Flight flight) throws Exception {
		try {
			tripService.endTrips(flight);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to end the trips");
		}
	}
}
