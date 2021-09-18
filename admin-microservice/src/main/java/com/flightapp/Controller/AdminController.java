package com.flightapp.Controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightapp.model.AirLine;
import com.flightapp.model.Flight;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/airline/register")
	public AirLine registerNewAirLine(@RequestBody AirLine airLine) {

		String restUrl = "http://localhost:8091/airlines/save";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<AirLine> entity = new HttpEntity<AirLine>(headers);
		ResponseEntity<AirLine> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, entity, AirLine.class);
		return responseEntity.getBody();

	}

	@PutMapping("/airline/delete")
	public AirLine deleteNewAirLine(@RequestBody AirLine airLine) {
		String restUrl = "http://localhost:8091/airlines/delete";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<AirLine> entity = new HttpEntity<AirLine>(headers);
		ResponseEntity<AirLine> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, entity, AirLine.class);
		return responseEntity.getBody();
	}

	@PutMapping("/flights/add")
	public Flight addNewFlight(@RequestBody Flight flight) {
		String restUrl = "http://localhost:8091/flights/add";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> entity = new HttpEntity<Flight>(headers);
		ResponseEntity<Flight> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, entity, Flight.class);

		// delete the airline

		return responseEntity.getBody();
	}

	@PutMapping("/flights/delete")
	public Flight deleteFlight(@RequestBody Flight flight) {
		String restUrl = "http://localhost:8091/flights/delete";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Flight> entity = new HttpEntity<Flight>(headers);
		ResponseEntity<Flight> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, entity, Flight.class);
		return responseEntity.getBody();
	}
}
